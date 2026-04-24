// LeetCode 2833 - Furthest Point From Origin
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func furthestDistanceFromOrigin(moves string) int {
	left := countMove(moves, 'L')
	right := countMove(moves, 'R')
	blank := countMove(moves, '_')

	if left > right {
		return left - right + blank
	}
	return right - left + blank
}

func countMove(moves string, target byte) int {
	total := 0
	for i := 0; i < len(moves); i++ {
		if moves[i] == target {
			total++
		}
	}
	return total
}
