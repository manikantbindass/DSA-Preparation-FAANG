// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subset that can be arranged into a
//   palindrome-like sequence where each element is the square of the
//   previous one, except the middle element which can appear once or
//   twice. The pattern is symmetric: [x, x^2, x^4, ..., x^(2^k), ..., x^4,
//   x^2, x]. This means we can think of building chains by repeatedly
//   squaring numbers. For each distinct number, we can follow its chain of
//   squares as long as we have at least two copies of each intermediate
//   number (except possibly the last one). The number 1 is special because
//   1^2 = 1, so it can form a chain of any length, but we can only use at
//   most one 1 in the middle (since it would be the center). The
//   algorithm: count frequencies of all numbers. For 1, the maximum length
//   is the largest odd number <= count (since we can place 1s
//   symmetrically around a single 1). For other numbers, we start from
//   each number and follow the chain: while we have at least 2 copies of
//   the current number, we move to its square and add 2 to length. At the
//   end, if we have at least one copy of the final number, we add 1 (for
//   the center). We track the maximum length found.
// 
// Complexity
//   Time  : O(n log log M) where M is max value, because each number's chain length is O(log log M) and we process each distinct number once
//   Space : O(n) for the frequency map
// 
// Runtime  : 1 ms
// Memory   : 42.7 MB
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

import java.util.HashMap;
import java.util.Map;

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
