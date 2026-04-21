// LeetCode 73 - Set Matrix Zeroes
// Time Complexity: O(mn) | Space Complexity: O(1)
package main

func setZeroes(matrix [][]int) {
	rows := len(matrix)
	cols := len(matrix[0])
	firstRowZero := false
	firstColZero := false

	for col := 0; col < cols; col++ {
		if matrix[0][col] == 0 {
			firstRowZero = true
			break
		}
	}

	for row := 0; row < rows; row++ {
		if matrix[row][0] == 0 {
			firstColZero = true
			break
		}
	}

	for row := 1; row < rows; row++ {
		for col := 1; col < cols; col++ {
			if matrix[row][col] == 0 {
				matrix[row][0] = 0
				matrix[0][col] = 0
			}
		}
	}

	for row := 1; row < rows; row++ {
		for col := 1; col < cols; col++ {
			if matrix[row][0] == 0 || matrix[0][col] == 0 {
				matrix[row][col] = 0
			}
		}
	}

	if firstRowZero {
		for col := 0; col < cols; col++ {
			matrix[0][col] = 0
		}
	}

	if firstColZero {
		for row := 0; row < rows; row++ {
			matrix[row][0] = 0
		}
	}
}
