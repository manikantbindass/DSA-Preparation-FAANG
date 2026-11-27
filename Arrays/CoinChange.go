// LeetCode Problem 322: Coin Change
// Difficulty: Medium
// Link: https://leetcode.com/problems/coin-change/
// Approach: Use complete knapsack to minimize the number of coins for each amount.
// Time Complexity: O(n*amount)
// Space Complexity: O(amount)

package coinchange

func coinChange(coins []int, amount int) int {
	unreachable := amount + 1
	dp := make([]int, amount+1)
	for total := 1; total <= amount; total++ {
		dp[total] = unreachable
	}
	for _, coin := range coins {
		for total := coin; total <= amount; total++ {
			dp[total] = min(dp[total], dp[total-coin]+1)
		}
	}
	if dp[amount] == unreachable {
		return -1
	}
	return dp[amount]
}
