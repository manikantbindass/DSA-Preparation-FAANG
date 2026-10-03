/*
 * LeetCode Problem 32: Longest Valid Parentheses
 * Problem Number: 32
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')', return the length of
 * the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 	0 <= s.length <= 3 * 104
 * 	s[i] is '(', or ')'.
 *
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 * Example 3:
 * Input: s = ""
 * Output: 0
 *
 * Constraints:
 * - 0 <= s.length <= 3 * 104
 * - s[i] is '(', or ')'.
 *
 * Topics: String, Dynamic Programming, Stack, Bracket Sequences
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 1 ms
 * Memory: 45 MB
 */

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
            }
        }
        return ans;
    }
}
