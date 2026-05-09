# LeetCode 62 - Unique Paths
# Time Complexity: O(m * n) | Space Complexity: O(m * n)


class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = [[0] * n for _ in range(m)]
        dp[0][0] = 1

        for row in range(m):
            for col in range(n):
                if row > 0:
                    dp[row][col] += dp[row - 1][col]
                if col > 0:
                    dp[row][col] += dp[row][col - 1]

        return dp[m - 1][n - 1]
