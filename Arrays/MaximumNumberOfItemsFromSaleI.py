# LeetCode Problem 3946: Maximum Number of Items From Sale I
# Difficulty: Medium
# Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
# Approach: Count multiples by factor, then use 0/1 knapsack for first purchases and the cheapest item for extra copies.
# Time Complexity: O(F log F + n*budget), where F is the maximum factor
# Space Complexity: O(F + budget)


class Solution:
    def maximumSaleItems(self, items: list[list[int]], budget: int) -> int:
        max_factor = max(factor for factor, _ in items)
        frequency = [0] * (max_factor + 1)
        for factor, _ in items:
            frequency[factor] += 1

        reward = [0] * (max_factor + 1)
        for factor in range(1, max_factor + 1):
            if frequency[factor]:
                reward[factor] = sum(frequency[factor::factor])

        cheapest = min(price for _, price in items)
        # Any budget left after first purchases can buy extra copies at this price.
        dp = [spend // cheapest for spend in range(budget + 1)]
        for factor, price in items:
            for spend in range(budget, price - 1, -1):
                dp[spend] = max(dp[spend], dp[spend - price] + reward[factor])
        return dp[budget]
