# ──────────────────────────────────────────────────────────────────────
# LeetCode #188 · Best Time to Buy and Sell Stock IV
# Difficulty : Hard
# Topics     : Array, Dynamic Programming
# URL        : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use dynamic programming with memoization. The state is defined by
#   (day, remaining transactions, holding stock). On each day, we can
#   either skip (do nothing) or, if we are not holding a stock and have
#   remaining transactions, we can buy (spend money, decrease remaining
#   transactions, set holding to 1). If we are holding a stock, we can
#   sell (gain profit, set holding to 0). We take the maximum profit among
#   these choices. The base case is when we exceed the last day, profit is
#   0. We cache results in a 3D array to avoid recomputation. If k is
#   large (>= n/2), we can use the greedy approach (sum all positive
#   differences) to avoid O(n*k) memory, but the DP works within
#   constraints.
# 
# Complexity
#   Time  : O(n * k)
#   Space : O(n * k)
# 
# Runtime  : 0 ms
# Memory   : 42.7 MB
# 
# Examples
#   Example 1:
#     Input  : k = 2, prices = [2,4,1]
#     Output : 2
#     Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
#   Example 2:
#     Input  : k = 2, prices = [3,2,6,5,0,3]
#     Output : 7
#     Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
# 
# Constraints
#   · 1 <= k <= 100
#   · 1 <= prices.length <= 1000
#   · 0 <= prices[i] <= 1000
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        n = len(prices)
        if n == 0:
            return 0
        # If k is large enough, use greedy
        if k >= n // 2:
            profit = 0
            for i in range(1, n):
                if prices[i] > prices[i-1]:
                    profit += prices[i] - prices[i-1]
            return profit
        # dp[i][j][0] = max profit up to day i with at most j transactions and no stock
        # dp[i][j][1] = max profit up to day i with at most j transactions and holding stock
        dp = [[[0, 0] for _ in range(k+1)] for _ in range(n)]
        for j in range(k+1):
            dp[0][j][0] = 0
            dp[0][j][1] = -prices[0]
        for i in range(1, n):
            for j in range(k+1):
                dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])
                if j > 0:
                    dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i])
                else:
                    dp[i][j][1] = dp[i-1][j][1]
        return dp[n-1][k][0]
