// LeetCode Problem 2567: Minimum Score by Changing Two Elements
// Difficulty: Medium
// Link: https://leetcode.com/problems/minimum-score-by-changing-two-elements/
// Approach: Sort and compare the three ranges left after removing two extreme elements.
// Time Complexity: O(n log n)
// Space Complexity: O(n) including sorting, O(1) apart from sorting

package minimumscorebychangingtwoelements

import "sort"

func minimizeSum(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	return min(nums[n-3]-nums[0], nums[n-2]-nums[1], nums[n-1]-nums[2])
}
