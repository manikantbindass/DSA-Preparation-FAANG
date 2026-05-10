// LeetCode 2770 - Maximum Number of Jumps to Reach the Last Index
// Time Complexity: O(n^2) | Space Complexity: O(n)
package main

func maximumJumps(nums []int, target int) int {
	n := len(nums)
	memo := make([]int, n)
	visited := make([]bool, n)

	var dfs func(int) int
	dfs = func(index int) int {
		if index == n-1 {
			return 0
		}

		if visited[index] {
			return memo[index]
		}

		answer := -(1 << 30)
		for next := index + 1; next < n; next++ {
			if abs(nums[index]-nums[next]) <= target {
				answer = max(answer, 1+dfs(next))
			}
		}

		visited[index] = true
		memo[index] = answer
		return answer
	}

	answer := dfs(0)
	if answer < 0 {
		return -1
	}
	return answer
}

func abs(value int) int {
	if value < 0 {
		return -value
	}
	return value
}

func max(first int, second int) int {
	if first > second {
		return first
	}
	return second
}
