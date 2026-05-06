// LeetCode 119 - Pascal's Triangle II
// Time Complexity: O(rowIndex^2) | Space Complexity: O(rowIndex)
package main

func getRow(rowIndex int) []int {
	row := make([]int, rowIndex+1)

	for index := 0; index <= rowIndex; index++ {
		row[index] = 1
	}

	for currentRow := 2; currentRow <= rowIndex; currentRow++ {
		for col := currentRow - 1; col > 0; col-- {
			row[col] += row[col-1]
		}
	}

	return row
}
