// LeetCode Problem 327: Count of Range Sum
// Difficulty: Hard
// Link: https://leetcode.com/problems/count-of-range-sum/
// Approach: Use a Fenwick tree over prefix sums to count earlier prefixes in the inclusive target interval.
// Time Complexity: O(n log n)
// Space Complexity: O(n)

package countofrangesum

import (
	"slices"
	"sort"
)

func countRangeSum(nums []int, lower int, upper int) int {
	prefixes := make([]int64, len(nums)+1)
	for i, value := range nums {
		prefixes[i+1] = prefixes[i] + int64(value)
	}
	ordered := slices.Clone(prefixes)
	slices.Sort(ordered)
	ordered = slices.Compact(ordered)
	tree := make([]int, len(ordered)+1)

	countFirst := func(size int) int {
		count := 0
		for ; size > 0; size -= size & -size {
			count += tree[size]
		}
		return count
	}

	answer := 0
	for _, prefix := range prefixes {
		left := sort.Search(len(ordered), func(i int) bool { return ordered[i] >= prefix-int64(upper) })
		right := sort.Search(len(ordered), func(i int) bool { return ordered[i] > prefix-int64(lower) })
		answer += countFirst(right) - countFirst(left)

		rank, _ := slices.BinarySearch(ordered, prefix)
		for index := rank + 1; index < len(tree); index += index & -index {
			tree[index]++
		}
	}
	return answer
}
