// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · filter-occupied-intervals
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/filter-occupied-intervals/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to compute the maximum sum by selecting k elements from
//   the array, where each selected element is multiplied by a factor that
//   decreases by 1 each time (starting from mul). To maximize the sum, we
//   should always pick the largest remaining elements and multiply them by
//   the largest factors. Therefore, we sort the array in ascending order
//   and then iterate from the largest element downwards, multiplying by
//   (mul - i) for i from 0 to k-1. The solution uses a greedy approach
//   with sorting.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.9 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import "sort"

func maxSum(nums []int, k int, mul int) int64 {
    sort.Ints(nums)
    var ans int64 = 0
    j := len(nums) - 1
    for i := 0; i < k; i++ {
        factor := int64(mul - i)
        if factor < 1 {
            factor = 1
        }
        ans += int64(nums[j]) * factor
        j--
    }
    return ans
}
