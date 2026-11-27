# LeetCode Problem 322: Coin Change
# Difficulty: Medium
# Link: https://leetcode.com/problems/coin-change/
# Approach: Use complete knapsack to minimize the number of coins for each amount.
# Time Complexity: O(n*amount)
# Space Complexity: O(amount)


class Solution:
    def coinChange(self, coins: list[int], amount: int) -> int:
        unreachable = amount + 1
        dp = [0] + [unreachable] * amount
        for coin in coins:
            for total in range(coin, amount + 1):
                dp[total] = min(dp[total], dp[total - coin] + 1)
        return -1 if dp[amount] == unreachable else dp[amount]
