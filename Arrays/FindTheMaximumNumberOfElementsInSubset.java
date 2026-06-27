// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subset that can be arranged into a
//   palindrome-like sequence where each element is the square of the
//   previous one until the middle, then mirrored back. The pattern is
//   essentially a sequence that goes up by squaring and then down by
//   square roots. For each distinct number x (except 1), we can build a
//   chain by repeatedly squaring as long as we have at least two copies of
//   the current number (to use both sides of the palindrome). When we can
//   no longer square (either because the next square is not present or we
//   have only one copy), we add one more if that number exists (to be the
//   center). For the number 1, it's special because 1^2 = 1, so we can
//   have any number of 1's, but the pattern must be symmetric: we can use
//   an odd number of 1's (since the center can be a single 1). So the
//   maximum length for 1 is the largest odd count ≤ total count of 1's. We
//   use a hash map to count frequencies, then iterate over distinct
//   numbers (excluding 1) to compute the longest chain starting from each.
//   The answer is the maximum over all chains and the chain from 1.
// 
// Complexity
//   Time  : O(n log max(nums))
//   Space : O(n)
// 
// Runtime  : 52 ms
// Memory   : 67.6 MB
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
