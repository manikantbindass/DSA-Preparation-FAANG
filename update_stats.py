"""Update the README Progress Dashboard with current LeetCode stats."""

from __future__ import annotations

import argparse
import os
import re
import sys
import time
from dataclasses import dataclass
from datetime import datetime, timezone
from pathlib import Path
from typing import Any

import requests


README_PATH = Path("README.md")
PUBLIC_API_URL = "https://leetcode-stats-api.herokuapp.com/{username}"
GRAPHQL_URL = "https://leetcode.com/graphql"
DEFAULT_USERNAME = "manikantbindass"
DEFAULT_GOAL = 300
MAX_ATTEMPTS = 3
TIMEOUT_SECONDS = 10


@dataclass(frozen=True)
class LeetCodeStats:
    ranking: int
    total_solved: int
    easy_solved: int
    medium_solved: int
    hard_solved: int


def request_with_retries(
    method: str,
    url: str,
    *,
    attempts: int = MAX_ATTEMPTS,
    **kwargs: Any,
) -> requests.Response:
    last_error: Exception | None = None

    for attempt in range(1, attempts + 1):
        try:
            response = requests.request(method, url, timeout=TIMEOUT_SECONDS, **kwargs)
            response.raise_for_status()
            return response
        except requests.RequestException as exc:
            last_error = exc
            if attempt < attempts:
                time.sleep(attempt)

    raise RuntimeError(f"API request failed after {attempts} attempts: {last_error}")


def parse_int(data: dict[str, Any], *keys: str) -> int:
    for key in keys:
        value = data.get(key)
        if value is not None:
            try:
                return int(value)
            except (TypeError, ValueError) as exc:
                raise ValueError(f"Invalid numeric field {key}: {value!r}") from exc
    raise KeyError(f"Missing required fields: {', '.join(keys)}")


def fetch_from_public_api(username: str) -> LeetCodeStats:
    response = request_with_retries("GET", PUBLIC_API_URL.format(username=username))
    payload = response.json()

    status = payload.get("status")
    if status == "error":
        message = payload.get("message", "unknown API error")
        raise RuntimeError(f"Public API returned an error: {message}")

    return LeetCodeStats(
        ranking=parse_int(payload, "ranking", "rank"),
        total_solved=parse_int(payload, "totalSolved", "total_solved"),
        easy_solved=parse_int(payload, "easySolved", "easy_solved"),
        medium_solved=parse_int(payload, "mediumSolved", "medium_solved"),
        hard_solved=parse_int(payload, "hardSolved", "hard_solved"),
    )


def fetch_from_graphql(username: str) -> LeetCodeStats:
    query = """
    query userProfile($username: String!) {
      matchedUser(username: $username) {
        profile {
          ranking
        }
        submitStatsGlobal {
          acSubmissionNum {
            difficulty
            count
          }
        }
      }
    }
    """
    headers = {
        "Content-Type": "application/json",
        "Referer": f"https://leetcode.com/{username}/",
        "User-Agent": "README stats updater",
    }
    response = request_with_retries(
        "POST",
        GRAPHQL_URL,
        json={"query": query, "variables": {"username": username}},
        headers=headers,
    )
    payload = response.json()
    user = payload.get("data", {}).get("matchedUser")
    if not user:
        raise RuntimeError(f"LeetCode user not found: {username}")

    solved_by_difficulty = {
        item["difficulty"]: int(item["count"])
        for item in user.get("submitStatsGlobal", {}).get("acSubmissionNum", [])
        if "difficulty" in item and "count" in item
    }

    return LeetCodeStats(
        ranking=parse_int(user.get("profile", {}), "ranking"),
        total_solved=solved_by_difficulty.get("All", 0),
        easy_solved=solved_by_difficulty.get("Easy", 0),
        medium_solved=solved_by_difficulty.get("Medium", 0),
        hard_solved=solved_by_difficulty.get("Hard", 0),
    )


def fetch_leetcode_stats(username: str) -> LeetCodeStats:
    errors: list[str] = []
    for fetcher in (fetch_from_public_api, fetch_from_graphql):
        try:
            return fetcher(username)
        except Exception as exc:  # noqa: BLE001 - keep fallback resilient in CI.
            errors.append(f"{fetcher.__name__}: {exc}")

    raise RuntimeError("Unable to fetch LeetCode stats:\n" + "\n".join(errors))


def format_rank(rank: int) -> str:
    return f"{rank:,}"


def render_dashboard(stats: LeetCodeStats, username: str, goal: int) -> str:
    goal_percent = (stats.total_solved / goal) * 100 if goal else 0
    progress_value = round(goal_percent)
    synced_at = datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M UTC")

    template = """## Progress Dashboard

<!-- LEETCODE-STATS:START -->
Last synced: {{LAST_SYNCED}}

### 🚀 LeetCode Stats

![LeetCode Rank](https://img.shields.io/badge/Rank-{{LEETCODE_RANK_BADGE}}-2563eb?style=for-the-badge&logo=leetcode&logoColor=white)
![Solved](https://img.shields.io/badge/Solved-{{TOTAL_SOLVED}}-22c55e?style=for-the-badge)
![Goal](https://img.shields.io/badge/Goal-{{TOTAL_SOLVED}}%2F{{GOAL}}-f59e0b?style=for-the-badge)

| Metric | Progress |
|---|---:|
| Rank | {{LEETCODE_RANK}} |
| Solved | {{TOTAL_SOLVED}} |
| Easy | {{EASY_SOLVED}} |
| Medium | {{MEDIUM_SOLVED}} |
| Hard | {{HARD_SOLVED}} |
| Goal progress | {{TOTAL_SOLVED}} / {{GOAL}}, {{GOAL_PERCENT}}% |

![Overall Goal Progress](https://progress-bar.xyz/{{PROGRESS_VALUE}}/?scale=100&title=Goal+{{TOTAL_SOLVED}}%2F{{GOAL}}&width=700&color=22c55e&suffix=%25)

```mermaid
pie showData
    title Solved Problems by Difficulty
    "Easy" : {{EASY_SOLVED}}
    "Medium" : {{MEDIUM_SOLVED}}
    "Hard" : {{HARD_SOLVED}}
```
<!-- LEETCODE-STATS:END -->
"""

    replacements = {
        "{{LAST_SYNCED}}": synced_at,
        "{{LEETCODE_USERNAME}}": username,
        "{{LEETCODE_RANK}}": format_rank(stats.ranking),
        "{{LEETCODE_RANK_BADGE}}": str(stats.ranking),
        "{{TOTAL_SOLVED}}": str(stats.total_solved),
        "{{EASY_SOLVED}}": str(stats.easy_solved),
        "{{MEDIUM_SOLVED}}": str(stats.medium_solved),
        "{{HARD_SOLVED}}": str(stats.hard_solved),
        "{{GOAL}}": str(goal),
        "{{GOAL_PERCENT}}": f"{goal_percent:.1f}",
        "{{PROGRESS_VALUE}}": str(progress_value),
    }
    for placeholder, value in replacements.items():
        template = template.replace(placeholder, value)
    return template


def update_readme(readme: Path, dashboard: str) -> bool:
    content = readme.read_text(encoding="utf-8")
    pattern = re.compile(r"## Progress Dashboard\n.*?(?=\n## Repository Map)", re.DOTALL)
    updated, count = pattern.subn(dashboard, content, count=1)
    if count != 1:
        raise RuntimeError("Could not find README Progress Dashboard section")

    if updated == content:
        return False

    readme.write_text(updated, encoding="utf-8", newline="\n")
    return True


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--readme", type=Path, default=README_PATH)
    parser.add_argument("--username", default=os.getenv("LEETCODE_USERNAME", DEFAULT_USERNAME))
    parser.add_argument("--goal", type=int, default=int(os.getenv("LEETCODE_GOAL", DEFAULT_GOAL)))
    args = parser.parse_args()

    stats = fetch_leetcode_stats(args.username)
    dashboard = render_dashboard(stats, args.username, args.goal)
    changed = update_readme(args.readme, dashboard)

    print(
        "README updated"
        if changed
        else "README already up to date"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
