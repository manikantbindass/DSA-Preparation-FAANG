// LeetCode Problem 4120: Count Dominant Elements
// Difficulty: Easy
// Link: https://leetcode.com/problems/count-dominant-elements/
// Approach: Compare each value times its neighbor count with the sum of its neighbors.
// Time Complexity: O(n)
// Space Complexity: O(1)

package countdominantelements

func countDominantElements(nums []int) int {
	answer := 0
	for i, value := range nums {
		neighborCount, neighborSum := 0, 0
		if i > 0 {
			neighborCount++
			neighborSum += nums[i-1]
		}
		if i+1 < len(nums) {
			neighborCount++
			neighborSum += nums[i+1]
		}
		if value*neighborCount >= neighborSum {
			answer++
		}
	}
	return answer
}
