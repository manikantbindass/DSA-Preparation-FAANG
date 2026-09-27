/*
 * LeetCode Problem 1298: Reverse Substrings Between Each Pair of Parentheses
 * Problem Number: 1298
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 *
 * You are given a string s that consists of lower case English letters and
 * brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the
 * innermost one.
 *
 * Your result should not contain any brackets.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 *
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is
 * reversed.
 *
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the
 * whole string.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 2000
 * 	s only contains lower case English characters and parentheses.
 * 	It is guaranteed that all parentheses are balanced.
 *
 * Example 1:
 * Input: s = "(abcd)"
 * Output: "dcba"
 *
 * Example 2:
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 *
 * Example 3:
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 *
 * Constraints:
 * - 1 <= s.length <= 2000
 * - s only contains lower case English characters and parentheses.
 * - It is guaranteed that all parentheses are balanced.
 *
 * Topics: String, Stack, Bracket Sequences
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.2 MB
 */

class Solution {
    public String reverseParentheses(String s) {
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder t = new StringBuilder();
                while (stk.charAt(stk.length() - 1) != '(') {
                    t.append(stk.charAt(stk.length() - 1));
                    stk.deleteCharAt(stk.length() - 1);
                }
                stk.deleteCharAt(stk.length() - 1);
                stk.append(t);
            } else {
                stk.append(c);
            }
        }
        return stk.toString();
    }
}
