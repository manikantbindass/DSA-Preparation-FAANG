// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subsequence that can be arranged into a
//   palindrome-like pattern where each element is the square of the
//   previous one until the middle, then mirrored. The pattern is
//   symmetric: [x, x^2, x^4, ..., x^(2^k), ..., x^4, x^2, x]. This means
//   we can think of building chains by repeatedly squaring numbers. For
//   each starting number, we can follow the chain of squares as long as we
//   have at least two copies of each intermediate number (except possibly
//   the middle element which can appear once). We also handle the special
//   case of 1, because 1^2 = 1, so any number of 1's can form a chain of
//   length 1 (or any odd length if we have enough copies). The algorithm
//   counts frequencies, then for each distinct number (except 1), we
//   traverse the chain of squares, counting pairs (2 each) until we hit a
//   number with count 1 or missing. For 1, we compute the maximum odd
//   length from its count. We take the maximum over all chains.
// 
// Complexity
//   Time  : O(n log log maxVal)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : nums = [5,4,1,2,2]
//     Output : 3
//     Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.
//   Example 2:
//     Input  : nums = [1,3,2,4]
//     Output : 1
//     Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer.
// 
// Constraints
//   · 2 <= nums.length <= 105
//   · 1 <= nums[i] <= 109
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge((long) x, 1, Integer::sum);
        }
        Integer t = cnt.remove(1L);
        int ans = t == null ? 0 : t - (t % 2 ^ 1);
        for (long x : cnt.keySet()) {
            t = 0;
            while (cnt.getOrDefault(x, 0) > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.getOrDefault(x, -1);
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
