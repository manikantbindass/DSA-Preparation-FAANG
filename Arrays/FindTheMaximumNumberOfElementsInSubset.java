// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subset that can be arranged into a
//   symmetric sequence where each element (except the middle) is the
//   square of the previous one. The pattern is like a palindrome of
//   powers: starting from some x, then x^2, x^4, ..., up to some peak,
//   then back down. This means the sequence is determined by a base number
//   and repeated squaring. We count frequencies of each number. For 1, the
//   pattern is just [1] or [1,1,1,...] but note that 1^2 = 1, so any
//   number of 1's can form a valid subset? Actually the pattern requires
//   that each step squares the previous, so for 1 it's always 1, so any
//   number of 1's works, but the pattern length must be odd? Let's think:
//   [1] is valid, [1,1] is not because 1^2 = 1, but the pattern expects
//   [x, x^2] which would be [1,1] but then the reverse part would be [1]?
//   Actually the pattern is symmetric: [x, x^2, x^4, ..., x^k, ..., x^4,
//   x^2, x]. For x=1, all elements are 1, so any odd length works? But the
//   pattern requires that the sequence is exactly that symmetric
//   structure. For 1, any length works because all are 1, but the pattern
//   forces the sequence to be symmetric and each step squares. Since 1^2 =
//   1, any sequence of all 1's of any length satisfies? Let's check: [1,1]
//   would be [x, x^2] = [1,1] and then the reverse part would be [1]?
//   Actually the pattern is [x, x^2, x^4, ..., x^k, x^{k/2}, ..., x^2, x]
//   so the length is always odd (2*m+1). For x=1, all elements are 1, so
//   any odd length works. But we can also have just [1] (length 1). So the
//   maximum number of 1's we can use is the largest odd number <=
//   count(1). That is count(1) if count(1) is odd, else count(1)-1. For
//   other numbers, we consider each distinct number as a potential start.
//   We try to build a chain by repeatedly squaring while we have at least
//   2 copies of the current number (to use both sides of the palindrome).
//   When we can't square further, we add one copy of the last number (the
//   peak) if available. The total length is 2 * (number of squaring steps)
//   + 1 (if peak exists) or 2 * steps (if no peak? Actually if we have at
//   least 2 copies at each step, we can form a palindrome of length
//   2*steps+1? Let's simulate: start with x, we need at least 2 copies of
//   x to put one on each side? Actually the pattern: [x, x^2, ..., peak,
//   ..., x^2, x]. So we need at least 2 copies of x (one at each end), 2
//   copies of x^2, etc., and 1 copy of the peak. So if we have at least 2
//   copies of each number in the chain except the peak, we can form a
//   palindrome. The length is 2 * (number of steps) + 1. If we don't have
//   a peak (i.e., we run out of copies before reaching a number with count
//   >=2), we can still form a palindrome of length 2 * steps? Actually if
//   we have only 1 copy of the last number, we can't use it as peak
//   because we need it for both sides? Wait, the pattern always has a
//   peak. If we have only 1 copy of the last number, we can use it as the
//   peak, but then we need 2 copies of all previous numbers. So the
//   algorithm: for each distinct number x (not 1), we try to build a
//   chain: while count[x] >= 2, we add 2 to length, then set x = x*x,
//   continue. After the loop, if count[x] >= 1, we can add 1 for the peak.
//   This gives the maximum length for that starting x. We take the maximum
//   over all x and also consider the 1 case. This matches the accepted
//   solution logic.
// 
// Complexity
//   Time  : O(n log log max)
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
