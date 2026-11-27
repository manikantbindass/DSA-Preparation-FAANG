// LeetCode Problem 315: Count of Smaller Numbers After Self
// Difficulty: Hard
// Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
// Approach: Scan from right to left and use a Fenwick tree over compressed values to count strictly smaller elements.
// Time Complexity: O(n log n)
// Space Complexity: O(n)

package countofsmallernumbersafterself

import "sort"

func countSmaller(nums []int) []int {
	ordered := append([]int(nil), nums...)
	sort.Ints(ordered)
	ranks := make(map[int]int)
	for _, value := range ordered {
		if ranks[value] == 0 {
			ranks[value] = len(ranks) + 1
		}
	}
	tree := make([]int, len(ranks)+1)
	answer := make([]int, len(nums))
	for i := len(nums) - 1; i >= 0; i-- {
		rank := ranks[nums[i]]
		for index := rank - 1; index > 0; index -= index & -index {
			answer[i] += tree[index]
		}
		for index := rank; index < len(tree); index += index & -index {
			tree[index]++
		}
	}
	return answer
}
