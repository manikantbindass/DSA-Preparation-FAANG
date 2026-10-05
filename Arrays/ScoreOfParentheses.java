/*
 * LeetCode Problem 886: Score of Parentheses
 * Problem Number: 886
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/score-of-parentheses/
 *
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * 	"()" has score 1.
 * 	AB has score A + B, where A and B are balanced parentheses strings.
 * 	(A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 *
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 *
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 *
 *
 *
 * Constraints:
 *
 * 	2 <= s.length <= 50
 * 	s consists of only '(' and ')'.
 * 	s is a balanced parentheses string.
 *
 * Example 1:
 * Input: s = "()"
 * Output: 1
 *
 * Example 2:
 * Input: s = "(())"
 * Output: 2
 *
 * Example 3:
 * Input: s = "()()"
 * Output: 2
 *
 * Constraints:
 * - 2 <= s.length <= 50
 * - s consists of only '(' and ')'.
 * - s is a balanced parentheses string.
 *
 * Topics: String, Stack, Bracket Sequences
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++d;
            } else {
                --d;
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << d;
                }
            }
        }
        return ans;
    }
}
