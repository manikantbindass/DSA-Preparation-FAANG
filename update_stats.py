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
from urllib.parse import quote

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


@dataclass(frozen=True)
class ContestStats:
    attended_contests_count: int
    contest_rating: float | None
    global_ranking: int | None
    top_percentage: float | None
    latest_contest_title: str | None
    latest_contest_start_time: int | None
    latest_ranking: int | None
    latest_problems_solved: int | None
    latest_total_problems: int | None


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


def fetch_contest_stats(username: str) -> ContestStats:
    query = """
    query userContest($username: String!) {
      userContestRanking(username: $username) {
        attendedContestsCount
        rating
        globalRanking
        topPercentage
      }
      userContestRankingHistory(username: $username) {
        attended
        rating
        ranking
        problemsSolved
        totalProblems
        contest {
          title
          startTime
        }
      }
    }
    """
    headers = {
        "Content-Type": "application/json",
        "Referer": f"https://leetcode.com/u/{username}/",
        "User-Agent": "README contest stats updater",
    }
    response = request_with_retries(
        "POST",
        GRAPHQL_URL,
        json={"query": query, "variables": {"username": username}},
        headers=headers,
    )
    payload = response.json().get("data", {})
    ranking = payload.get("userContestRanking") or {}
    history = [
        item
        for item in (payload.get("userContestRankingHistory") or [])
        if item.get("attended")
    ]
    latest = max(
        history,
        key=lambda item: int(item.get("contest", {}).get("startTime") or 0),
        default=None,
    )

    return ContestStats(
        attended_contests_count=int(ranking.get("attendedContestsCount") or len(history)),
        contest_rating=float(ranking["rating"]) if ranking.get("rating") is not None else None,
        global_ranking=int(ranking["globalRanking"]) if ranking.get("globalRanking") else None,
        top_percentage=float(ranking["topPercentage"]) if ranking.get("topPercentage") else None,
        latest_contest_title=latest.get("contest", {}).get("title") if latest else None,
        latest_contest_start_time=(
            int(latest.get("contest", {}).get("startTime"))
            if latest and latest.get("contest", {}).get("startTime") is not None
            else None
        ),
        latest_ranking=int(latest["ranking"]) if latest and latest.get("ranking") else None,
        latest_problems_solved=(
            int(latest["problemsSolved"])
            if latest and latest.get("problemsSolved") is not None
            else None
        ),
        latest_total_problems=(
            int(latest["totalProblems"])
            if latest and latest.get("totalProblems") is not None
            else None
        ),
    )


def format_rank(rank: int) -> str:
    return f"{rank:,}"


def format_contest_rank(rank: int | None) -> str:
    return format_rank(rank) if rank is not None else "Not available from public API"


def format_contest_rating(rating: float | None) -> str:
    return f"{rating:.1f}" if rating is not None else "Not available from public API"


def format_contest_top_percentage(top_percentage: float | None) -> str:
    return (
        f"{top_percentage:.2f}%"
        if top_percentage is not None
        else "Not available from public API"
    )


def format_contest_date(start_time: int | None) -> str:
    if start_time is None:
        return "Not available"
    return datetime.fromtimestamp(start_time, tz=timezone.utc).strftime("%Y-%m-%d %H:%M UTC")


def format_contest_result(solved: int | None, total: int | None) -> str:
    if solved is None or total is None:
        return "Not available"
    return f"{solved} / {total} solved"


def badge_text(value: str) -> str:
    return quote(value, safe="")


def render_dashboard(
    stats: LeetCodeStats,
    contest_stats: ContestStats,
    username: str,
    goal: int,
) -> str:
    goal_percent = (stats.total_solved / goal) * 100 if goal else 0
    progress_value = round(goal_percent)
    synced_at = datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M UTC")
    latest_contest_title = contest_stats.latest_contest_title or "No public contest history"

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

### 🏁 Contest Snapshot

![Contest Participation](https://img.shields.io/badge/Participations-{{CONTEST_PARTICIPATIONS_BADGE}}-8b5cf6?style=for-the-badge)
![Contest Rating](https://img.shields.io/badge/Contest_Rating-{{CONTEST_RATING_BADGE}}-0ea5e9?style=for-the-badge)
![Top Percentage](https://img.shields.io/badge/Top-{{CONTEST_TOP_PERCENTAGE_BADGE}}-f97316?style=for-the-badge)

| Contest Metric | Value |
|---|---:|
| Participations | {{CONTEST_PARTICIPATIONS}} |
| Contest rating | {{CONTEST_RATING}} |
| Global ranking | {{CONTEST_GLOBAL_RANK}} |
| Top percentage | {{CONTEST_TOP_PERCENTAGE}} |
| Latest recorded contest | {{LATEST_CONTEST_TITLE}} |
| Latest recorded date | {{LATEST_CONTEST_DATE}} |
| Latest recorded result | {{LATEST_CONTEST_RESULT}} |
| Latest recorded rank | {{LATEST_CONTEST_RANK}} |

> Contest rating and global ranking show `Not available from public API` when LeetCode's public GraphQL returns `null` or `0` for the profile.
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
        "{{CONTEST_PARTICIPATIONS}}": str(contest_stats.attended_contests_count),
        "{{CONTEST_PARTICIPATIONS_BADGE}}": badge_text(str(contest_stats.attended_contests_count)),
        "{{CONTEST_RATING}}": format_contest_rating(contest_stats.contest_rating),
        "{{CONTEST_RATING_BADGE}}": badge_text(
            f"{contest_stats.contest_rating:.1f}"
            if contest_stats.contest_rating is not None
            else "N/A"
        ),
        "{{CONTEST_GLOBAL_RANK}}": format_contest_rank(contest_stats.global_ranking),
        "{{CONTEST_TOP_PERCENTAGE}}": format_contest_top_percentage(contest_stats.top_percentage),
        "{{CONTEST_TOP_PERCENTAGE_BADGE}}": badge_text(
            f"{contest_stats.top_percentage:.2f}%"
            if contest_stats.top_percentage is not None
            else "N/A"
        ),
        "{{LATEST_CONTEST_TITLE}}": latest_contest_title,
        "{{LATEST_CONTEST_DATE}}": format_contest_date(contest_stats.latest_contest_start_time),
        "{{LATEST_CONTEST_RESULT}}": format_contest_result(
            contest_stats.latest_problems_solved,
            contest_stats.latest_total_problems,
        ),
        "{{LATEST_CONTEST_RANK}}": format_contest_rank(contest_stats.latest_ranking),
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
    contest_stats = fetch_contest_stats(args.username)
    dashboard = render_dashboard(stats, contest_stats, args.username, args.goal)
    changed = update_readme(args.readme, dashboard)

    print(
        "README updated"
        if changed
        else "README already up to date"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
