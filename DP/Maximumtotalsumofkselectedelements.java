// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · maximum-total-sum-of-k-selected-elements
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/maximum-total-sum-of-k-selected-elements/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires selecting exactly k elements from the array to
//   maximize the total sum, where each selected element is multiplied by a
//   factor that decreases by 1 for each subsequent selection (starting
//   from mul). The optimal strategy is to sort the array in ascending
//   order and then pick the k largest elements, assigning the largest
//   multiplier to the largest element, the next largest multiplier to the
//   next largest, and so on. This is because the multipliers are
//   decreasing, so to maximize the sum we pair the largest numbers with
//   the largest multipliers. The solution sorts the array, then iterates k
//   times, each time taking the next largest element (from the end) and
//   multiplying it by the current factor (mul - i), where i is the 0-based
//   index of selection. The factor is at least 1, so we use Math.max(1,
//   mul - i). The result is accumulated as a long to avoid overflow.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(1)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import java.util.Arrays;

class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        long ans = 0;
        int j = nums.length - 1;
        for (int i = 0; i < k; i++) {
            long factor = Math.max(1L, (long) mul - i);
            ans += (long) nums[j--] * factor;
        }
        return ans;
    }
}
