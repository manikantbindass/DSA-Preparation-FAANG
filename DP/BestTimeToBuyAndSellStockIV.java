// ──────────────────────────────────────────────────────────────────────
// LeetCode #188 · Best Time to Buy and Sell Stock IV
// Difficulty : Hard
// Topics     : Array, Dynamic Programming
// URL        : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use dynamic programming with memoization. The state is defined by
//   (day index, remaining transactions, holding status). At each day, we
//   can either skip the day or perform a transaction (buy if not holding
//   and have transactions left, sell if holding). The recursion explores
//   both options and returns the maximum profit. Base case: when we reach
//   the end of the array, profit is 0. We memoize results to avoid
//   recomputation.
// 
// Complexity
//   Time  : O(n * k)
//   Space : O(n * k)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : k = 2, prices = [2,4,1]
//     Output : 2
//     Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//   Example 2:
//     Input  : k = 2, prices = [3,2,6,5,0,3]
//     Output : 7
//     Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
// 
// Constraints
//   · 1 <= k <= 100
//   · 1 <= prices.length <= 1000
//   · 0 <= prices[i] <= 1000
// ──────────────────────────────────────────────────────────────────────

class Solution {
    private Integer[][][] memo;
    private int[] prices;
    private int n;

    public int maxProfit(int k, int[] prices) {
        this.prices = prices;
        n = prices.length;
        memo = new Integer[n][k + 1][2];
        return dfs(0, k, 0);
    }

    private int dfs(int day, int transactionsLeft, int holding) {
        if (day >= n) {
            return 0;
        }
        if (memo[day][transactionsLeft][holding] != null) {
            return memo[day][transactionsLeft][holding];
        }
        // skip the day
        int best = dfs(day + 1, transactionsLeft, holding);
        if (holding == 1) {
            // sell
            best = Math.max(best, prices[day] + dfs(day + 1, transactionsLeft, 0));
        } else if (transactionsLeft > 0) {
            // buy
            best = Math.max(best, -prices[day] + dfs(day + 1, transactionsLeft - 1, 1));
        }
        memo[day][transactionsLeft][holding] = best;
        return best;
    }
}
