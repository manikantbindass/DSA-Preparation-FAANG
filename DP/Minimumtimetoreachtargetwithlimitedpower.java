// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · minimum-time-to-reach-target-with-limited-power
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/minimum-time-to-reach-target-with-limited-power/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to find the maximum subarray sum after optionally
//   multiplying or dividing one element by k. The solution uses prefix and
//   suffix maximum subarray sums (Kadane's algorithm) to compute the best
//   subarray that includes a modified element. For each index i, we
//   consider two cases: multiply nums[i] by k or divide by k (integer
//   division). We compute the best subarray ending at i (left side) and
//   starting at i (right side), then combine them. The answer is the
//   maximum over all i and both operations.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 1 ms
// Memory   : 42.7 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] suf = new long[n];
        long[] pre = new long[n];
        suf[0] = nums[0];
        for (int i = 1; i < n; i++)
            suf[i] = Math.max(nums[i], suf[i - 1] + nums[i]);
        pre[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            pre[i] = Math.max(nums[i], pre[i + 1] + nums[i]);
        long[] lft = new long[n];
        long[] rgt = new long[n];
        for (int i = 0; i < n; i++) {
            lft[i] = Math.max(0, suf[i]);
            rgt[i] = Math.max(0, pre[i]);
        }
        long ans = Math.max(best(nums, k, true, lft, rgt, n),
                            best(nums, k, false, lft, rgt, n));
        return ans;
    }
    private long best(int[] nums, int k, boolean mul, long[] lft, long[] rgt, int n) {
        long top = Long.MIN_VALUE;
        long cur = 0;
        for (int i = 0; i < n; i++) {
            long val = mul ? (long) nums[i] * k : (long) nums[i] / k;
            long ext = (i == 0) ? 0 : Math.max(cur, lft[i - 1]);
            cur = val + ext;
            long tail = (i + 1 < n) ? rgt[i + 1] : 0;
            top = Math.max(top, cur + tail);
        }
        return top;
    }
}
