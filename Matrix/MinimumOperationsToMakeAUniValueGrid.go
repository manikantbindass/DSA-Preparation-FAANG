// LeetCode 2033 - Minimum Operations to Make a Uni-Value Grid
// Time Complexity: O(m * n * log(m * n)) | Space Complexity: O(m * n)
package main

import "sort"

func minOperations(grid [][]int, x int) int {
	remainder := grid[0][0] % x
	values := make([]int, 0, len(grid)*len(grid[0]))

	for _, row := range grid {
		for _, value := range row {
			if value%x != remainder {
				return -1
			}
			values = append(values, value)
		}
	}

	sort.Ints(values)
	median := values[len(values)/2]
	operations := 0

	for _, value := range values {
		operations += abs(value-median) / x
	}

	return operations
}

func abs(value int) int {
	if value < 0 {
		return -value
	}
	return value
}
