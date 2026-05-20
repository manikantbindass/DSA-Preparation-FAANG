# LeetCode 279 - Perfect Squares
# Time Complexity: O(n * sqrt(n)) | Space Complexity: O(n)


class Solution:
    def numSquares(self, n: int) -> int:
        dp = [0] * (n + 1)

        for value in range(1, n + 1):
            dp[value] = value
            square = 1
            while square * square <= value:
                dp[value] = min(dp[value], dp[value - square * square] + 1)
                square += 1

        return dp[n]
