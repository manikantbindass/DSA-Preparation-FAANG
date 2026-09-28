/*
 * LeetCode Problem 1737: Maximum Nesting Depth of the Parentheses
 * Problem Number: 1737
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 *
 * Given a valid parentheses string s, return the nesting depth of s. The nesting
 * depth is the maximum number of nested parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(1+(2*3)+((8)/4))+1"
 *
 * Output: 3
 *
 * Explanation:
 *
 * Digit 8 is inside of 3 nested parentheses in the string.
 *
 * Example 2:
 *
 * Input: s = "(1)+((2))+(((3)))"
 *
 * Output: 3
 *
 * Explanation:
 *
 * Digit 3 is inside of 3 nested parentheses in the string.
 *
 * Example 3:
 *
 * Input: s = "()(())((()()))"
 *
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 100
 * 	s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 * 	It is guaranteed that parentheses expression s is a VPS.
 *
 * Example 1:
 * Input: s = "(1+(2*3)+((8)/4))+1"
 * Output: 3
 *
 * Example 2:
 * Input: s = "(1)+((2))+(((3)))"
 * Output: 3
 *
 * Example 3:
 * Input: s = "()(())((()()))"
 * Output: 3
 *
 * Constraints:
 * - 1 <= s.length <= 100
 * - s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 * - It is guaranteed that parentheses expression s is a VPS.
 *
 * Topics: String, Stack, Bracket Sequences
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.3 MB
 */

class Solution {
    public int maxDepth(String s) {
        int ans=0,d=0;
        for (int i=0;i<s.length();++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ans = Math.max(ans, ++d);
            } else if (c== ')') {
                --d;
            }
        }
        return ans;
    }
}
