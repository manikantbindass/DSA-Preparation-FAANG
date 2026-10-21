/*
 * LeetCode Problem 4220: Minimum Operations to Sort a String
 * Problem Number: 4220
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-operations-to-sort-a-string/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * In one operation, you can select any substring of s that is not the entire
 * string and sort it in non-descending alphabetical order.
 *
 * Return the minimum number of operations required to make s sorted in
 * non-descending order. If it is not possible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dog"
 *
 * Output: 1
 *
 * Explanation:​​​​​​​
 *
 * 	Sort substring "og" to "go".
 * 	Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.
 *
 * Example 2:
 *
 * Input: s = "card"
 *
 * Output: 2
 *
 * Explanation:
 *
 * 	Sort substring "car" to "acr", so s = "acrd".
 * 	Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending
 * order. Thus, the answer is 2.
 *
 * Example 3:
 *
 * Input: s = "gf"
 *
 * Output: -1
 *
 * Explanation:
 *
 * 	It is impossible to sort s under the given constraints. Thus, the answer is -1.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 105
 * 	s consists of only lowercase English letters.
 *
 * Example 1:
 * Input: s = "dog"
 * Output: 1
 * Explanation: ​​​​​​​
 *
 * Example 2:
 * Input: s = "card"
 * Output: 2
 *
 * Example 3:
 * Input: s = "gf"
 * Output: -1
 *
 * Constraints:
 * - 1 <= s.length <= 105
 * - s consists of only lowercase English letters.
 *
 * Topics: String
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 41.6 MB
 */

class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        if (isSorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        return 3;
    }
}
