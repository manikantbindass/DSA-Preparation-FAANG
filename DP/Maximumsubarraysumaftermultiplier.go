// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · maximum-subarray-sum-after-multiplier
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/maximum-subarray-sum-after-multiplier/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to find the maximum subarray sum after applying a
//   multiplier to each element. The solution uses Kadane's algorithm with
//   a twist: we track the maximum subarray sum ending at each position,
//   but we also consider the effect of the multiplier. The algorithm
//   iterates through the array, maintaining two values: the maximum
//   subarray sum ending at the current position (including the multiplier
//   effect) and the overall maximum. At each step, we compute the new
//   ending sum as max(current element * multiplier, previous ending sum +
//   current element * multiplier). This handles the case where starting a
//   new subarray is better than extending the previous one. The overall
//   maximum is updated accordingly. The solution is O(n) time and O(1)
//   space.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.4 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func maxSubarraySumAfterMultiplier(nums []int, multiplier int) int {
    maxEndingHere := nums[0] * multiplier
    maxSoFar := maxEndingHere
    for i := 1; i < len(nums); i++ {
        current := nums[i] * multiplier
        if current > maxEndingHere + current {
            maxEndingHere = current
        } else {
            maxEndingHere = maxEndingHere + current
        }
        if maxEndingHere > maxSoFar {
            maxSoFar = maxEndingHere
        }
    }
    return maxSoFar
}
