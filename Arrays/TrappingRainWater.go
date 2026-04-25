// LeetCode 42 - Trapping Rain Water
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func trap(height []int) int {
	n := len(height)
	if n == 0 {
		return 0
	}

	left := make([]int, n)
	right := make([]int, n)
	left[0] = height[0]
	right[n-1] = height[n-1]

	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], height[i])
		right[n-i-1] = max(right[n-i], height[n-i-1])
	}

	trapped := 0
	for i := 0; i < n; i++ {
		if left[i] < right[i] {
			trapped += left[i] - height[i]
		} else {
			trapped += right[i] - height[i]
		}
	}

	return trapped
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
