// LeetCode 279 - Perfect Squares
// Time Complexity: O(n * sqrt(n)) | Space Complexity: O(n)
package main

func numSquares(n int) int {
	dp := make([]int, n+1)

	for value := 1; value <= n; value++ {
		dp[value] = value
		for square := 1; square*square <= value; square++ {
			dp[value] = min(dp[value], dp[value-square*square]+1)
		}
	}

	return dp[n]
}

func min(first int, second int) int {
	if first < second {
		return first
	}
	return second
}
