// ──────────────────────────────────────────────────────────────────────
// LeetCode #209 · Minimum Size Subarray Sum
// Difficulty : Medium
// Topics     : Array, Binary Search, Sliding Window, Prefix Sum
// URL        : https://leetcode.com/problems/minimum-size-subarray-sum/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a sliding window approach with two pointers (left and right) to
//   maintain a window whose sum is at least target. We expand the window
//   by moving the right pointer and adding nums[right] to the current sum.
//   When the sum becomes >= target, we try to shrink the window from the
//   left to find the minimal length, updating the answer accordingly. This
//   yields O(n) time and O(1) space. For the O(n log n) follow-up, we can
//   use prefix sums and binary search: compute prefix sums, then for each
//   starting index i, binary search for the smallest j such that prefix[j]
//   - prefix[i] >= target, and update the minimal length.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.6 MB
// 
// Examples
//   Example 1:
//     Input  : target = 7, nums = [2,3,1,2,4,3]
//     Output : 2
//     Explanation: The subarray [4,3] has the minimal length under the problem constraint.
//   Example 2:
//     Input  : target = 4, nums = [1,4,4]
//     Output : 1
//   Example 3:
//     Input  : target = 11, nums = [1,1,1,1,1,1,1,1]
//     Output : 0
// 
// Constraints
//   · 1 <= target <= 109
//   · 1 <= nums.length <= 105
//   · 1 <= nums[i] <= 104
// ──────────────────────────────────────────────────────────────────────

func minSubArrayLen(target int, nums []int) int {
    n := len(nums)
    left := 0
    sum := 0
    ans := n + 1
    for right := 0; right < n; right++ {
        sum += nums[right]
        for sum >= target {
            if right-left+1 < ans {
                ans = right - left + 1
            }
            sum -= nums[left]
            left++
        }
    }
    if ans == n+1 {
        return 0
    }
    return ans
}
