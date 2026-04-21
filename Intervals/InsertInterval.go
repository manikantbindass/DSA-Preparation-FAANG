// LeetCode 57 - Insert Interval
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func insert(intervals [][]int, newInterval []int) [][]int {
	merged := make([][]int, 0, len(intervals)+1)
	i := 0

	for i < len(intervals) && intervals[i][1] < newInterval[0] {
		merged = append(merged, intervals[i])
		i++
	}

	for i < len(intervals) && intervals[i][0] <= newInterval[1] {
		if intervals[i][0] < newInterval[0] {
			newInterval[0] = intervals[i][0]
		}
		if intervals[i][1] > newInterval[1] {
			newInterval[1] = intervals[i][1]
		}
		i++
	}
	merged = append(merged, newInterval)

	for i < len(intervals) {
		merged = append(merged, intervals[i])
		i++
	}

	return merged
}
