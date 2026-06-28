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

func maxProfit(k int, prices []int) int {
    n := len(prices)
    memo := make([][][]int, n)
    for i := 0; i < n; i++ {
        memo[i] = make([][]int, k+1)
        for j := 0; j <= k; j++ {
            memo[i][j] = []int{-1, -1}
        }
    }

    var dfs func(day int, transactionsLeft int, holding int) int
    dfs = func(day int, transactionsLeft int, holding int) int {
        if day >= n {
            return 0
        }
        if memo[day][transactionsLeft][holding] != -1 {
            return memo[day][transactionsLeft][holding]
        }
        best := dfs(day+1, transactionsLeft, holding)
        if holding == 1 {
            // sell
            if sell := prices[day] + dfs(day+1, transactionsLeft, 0); sell > best {
                best = sell
            }
        } else if transactionsLeft > 0 {
            // buy
            if buy := -prices[day] + dfs(day+1, transactionsLeft-1, 1); buy > best {
                best = buy
            }
        }
        memo[day][transactionsLeft][holding] = best
        return best
    }

    return dfs(0, k, 0)
}
