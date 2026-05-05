# LeetCode 120 - Triangle
# Time Complexity: O(n^2) | Space Complexity: O(n^2)
from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        dp = [[0] * (n + 1) for _ in range(n + 1)]

        for row in range(n - 1, -1, -1):
            for col in range(row + 1):
                dp[row][col] = min(dp[row + 1][col], dp[row + 1][col + 1]) + triangle[row][col]

        return dp[0][0]
