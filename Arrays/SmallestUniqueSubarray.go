// LeetCode Problem 3934: Smallest Unique Subarray
// Difficulty: Hard
// Link: https://leetcode.com/problems/smallest-unique-subarray/
// Approach: Build a suffix array by rank doubling, then use neighboring common prefix lengths to find unique prefixes.
// Time Complexity: O(n log n + M), where M is the maximum value
// Space Complexity: O(n + M)

package smallestuniquesubarray

import "sort"

func smallestUniqueSubarray(nums []int) int {
	n := len(nums)
	suffixes := uniqueSubarraySuffixArray(nums)
	positions := make([]int, n)
	for rank, start := range suffixes {
		positions[start] = rank
	}

	// lcp[rank] is the common prefix with the preceding suffix.
	lcp := make([]int, n+1)
	common := 0
	for start, rank := range positions {
		if rank == 0 {
			common = 0
			continue
		}
		previous := suffixes[rank-1]
		for start+common < n && previous+common < n && nums[start+common] == nums[previous+common] {
			common++
		}
		lcp[rank] = common
		common = max(0, common-1)
	}

	answer := n
	for rank, start := range suffixes {
		length := max(lcp[rank], lcp[rank+1]) + 1
		if length <= n-start {
			answer = min(answer, length)
		}
	}
	return answer
}

func uniqueSubarraySuffixArray(nums []int) []int {
	n := len(nums)
	suffixes := make([]int, n)
	for i := range suffixes {
		suffixes[i] = i
	}
	sort.Slice(suffixes, func(i, j int) bool { return nums[suffixes[i]] < nums[suffixes[j]] })

	ranks := make([]int, n)
	classes := 1
	for i := 1; i < n; i++ {
		if nums[suffixes[i-1]] != nums[suffixes[i]] {
			classes++
		}
		ranks[suffixes[i]] = classes - 1
	}

	shifted, nextRanks, counts := make([]int, n), make([]int, n), make([]int, n)
	for width := 1; classes < n; width *= 2 {
		// The second half is empty for the last width suffixes.
		for i := 0; i < width; i++ {
			shifted[i] = n - width + i
		}
		index := width
		for _, start := range suffixes {
			if start >= width {
				shifted[index] = start - width
				index++
			}
		}

		clear(counts[:classes])
		for _, start := range shifted {
			counts[ranks[start]]++
		}
		for i := 1; i < classes; i++ {
			counts[i] += counts[i-1]
		}
		for i := n - 1; i >= 0; i-- {
			start := shifted[i]
			rank := ranks[start]
			counts[rank]--
			suffixes[counts[rank]] = start
		}

		classes = 1
		previous := suffixes[0]
		nextRanks[previous] = 0
		for _, start := range suffixes[1:] {
			left, right := -1, -1
			if previous+width < n {
				left = ranks[previous+width]
			}
			if start+width < n {
				right = ranks[start+width]
			}
			if ranks[previous] != ranks[start] || left != right {
				classes++
			}
			nextRanks[start] = classes - 1
			previous = start
		}
		ranks, nextRanks = nextRanks, ranks
	}
	return suffixes
}
