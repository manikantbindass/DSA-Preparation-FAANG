// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · maximum-valid-pair-sum
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/maximum-valid-pair-sum/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to check if the middle element of the array (when the
//   array length is odd) appears exactly once. The solution counts
//   occurrences of the middle element and returns true only if the count
//   is 1. This works for arrays of odd length; for even length, the middle
//   index is ambiguous, but the problem likely assumes odd length or the
//   middle element is defined as the element at index n/2 (integer
//   division). The algorithm is O(n) time and O(1) space.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.7 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func isMiddleElementUnique(nums []int) bool {
    mid := nums[len(nums)/2]
    count := 0
    for _, v := range nums {
        if v == mid {
            count++
        }
    }
    return count == 1
}
