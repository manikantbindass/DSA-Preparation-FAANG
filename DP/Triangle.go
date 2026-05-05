// LeetCode 120 - Triangle
// Time Complexity: O(n^2) | Space Complexity: O(n^2)
package main

func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	dp := make([][]int, n+1)

	for row := 0; row <= n; row++ {
		dp[row] = make([]int, n+1)
	}

	for row := n - 1; row >= 0; row-- {
		for col := 0; col <= row; col++ {
			left := dp[row+1][col]
			right := dp[row+1][col+1]
			if left < right {
				dp[row][col] = left + triangle[row][col]
			} else {
				dp[row][col] = right + triangle[row][col]
			}
		}
	}

	return dp[0][0]
}
