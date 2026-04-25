// LeetCode 48 - Rotate Image
// Time Complexity: O(n^2) | Space Complexity: O(1)
package main

func rotate(matrix [][]int) {
	n := len(matrix)

	for row := 0; row < n; row++ {
		for col := row + 1; col < n; col++ {
			matrix[row][col], matrix[col][row] = matrix[col][row], matrix[row][col]
		}
	}

	for row := 0; row < n; row++ {
		left := 0
		right := n - 1
		for left < right {
			matrix[row][left], matrix[row][right] = matrix[row][right], matrix[row][left]
			left++
			right--
		}
	}
}
