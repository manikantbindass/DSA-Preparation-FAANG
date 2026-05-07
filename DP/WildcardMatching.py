# LeetCode 44 - Wildcard Matching
# Time Complexity: O(m * n) | Space Complexity: O(m * n)
from functools import lru_cache


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        text_length = len(s)
        pattern_length = len(p)

        @lru_cache(maxsize=None)
        def dfs(text_index: int, pattern_index: int) -> bool:
            if text_index >= text_length:
                return pattern_index >= pattern_length or (
                    p[pattern_index] == "*" and dfs(text_index, pattern_index + 1)
                )

            if pattern_index >= pattern_length:
                return False

            if p[pattern_index] == "*":
                return (
                    dfs(text_index + 1, pattern_index)
                    or dfs(text_index + 1, pattern_index + 1)
                    or dfs(text_index, pattern_index + 1)
                )

            return (p[pattern_index] == "?" or s[text_index] == p[pattern_index]) and dfs(
                text_index + 1, pattern_index + 1
            )

        return dfs(0, 0)
