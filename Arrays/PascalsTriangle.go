// LeetCode 118 - Pascal's Triangle
// Time Complexity: O(numRows^2) | Space Complexity: O(numRows^2)
package main

func generate(numRows int) [][]int {
	answer := make([][]int, 0, numRows)

	for row := 0; row < numRows; row++ {
		current := []int{1}

		for col := 1; col < row; col++ {
			value := answer[row-1][col-1] + answer[row-1][col]
			current = append(current, value)
		}

		if row > 0 {
			current = append(current, 1)
		}

		answer = append(answer, current)
	}

	return answer
}
