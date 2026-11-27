// LeetCode Problem 3946: Maximum Number of Items From Sale I
// Difficulty: Medium
// Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
// Approach: Count multiples by factor, then use 0/1 knapsack for first purchases and the cheapest item for extra copies.
// Time Complexity: O(F log F + n*budget), where F is the maximum factor
// Space Complexity: O(F + budget)

package maximumnumberofitemsfromsalei

func maximumSaleItems(items [][]int, budget int) int {
	maxFactor, cheapest := 0, items[0][1]
	for _, item := range items {
		maxFactor = max(maxFactor, item[0])
		cheapest = min(cheapest, item[1])
	}

	frequency := make([]int, maxFactor+1)
	for _, item := range items {
		frequency[item[0]]++
	}
	reward := make([]int, maxFactor+1)
	for factor := 1; factor <= maxFactor; factor++ {
		if frequency[factor] == 0 {
			continue
		}
		for multiple := factor; multiple <= maxFactor; multiple += factor {
			reward[factor] += frequency[multiple]
		}
	}

	// Any budget left after first purchases can buy extra copies at this price.
	dp := make([]int, budget+1)
	for spend := range dp {
		dp[spend] = spend / cheapest
	}
	for _, item := range items {
		factor, price := item[0], item[1]
		for spend := budget; spend >= price; spend-- {
			dp[spend] = max(dp[spend], dp[spend-price]+reward[factor])
		}
	}
	return dp[budget]
}
