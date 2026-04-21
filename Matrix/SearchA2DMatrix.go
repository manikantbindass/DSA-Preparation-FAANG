// LeetCode 74 - Search a 2D Matrix
// Time Complexity: O(log(mn)) | Space Complexity: O(1)
package main

func searchMatrix(matrix [][]int, target int) bool {
	rows := len(matrix)
	cols := len(matrix[0])
	left := 0
	right := rows*cols - 1

	for left <= right {
		mid := left + (right-left)/2
		value := matrix[mid/cols][mid%cols]

		if value == target {
			return true
		}
		if value < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}

	return false
}
