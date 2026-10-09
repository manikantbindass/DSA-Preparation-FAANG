/*
 * LeetCode Problem 1648: Minimum Insertions to Balance a Parentheses String
 * Problem Number: 1648
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 *
 * Given a parentheses string s containing only the characters '(' and ')'. A
 * parentheses string is balanced if:
 *
 * 	Any left parenthesis '(' must have a corresponding two consecutive right
 * parenthesis '))'.
 * 	Left parenthesis '(' must go before the corresponding two consecutive right
 * parenthesis '))'.
 *
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing
 * parenthesis.
 *
 * 	For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and
 * "(()))" are not balanced.
 *
 * You can insert the characters '(' and ')' at any position of the string to
 * balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only
 * ')' matching. We need to add one more ')' at the end of the string to be
 * "(())))" which is balanced.
 *
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 *
 * Example 3:
 *
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 105
 * 	s consists of '(' and ')' only.
 *
 * Example 1:
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
 *
 * Example 2:
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 *
 * Example 3:
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 *
 * Constraints:
 * - 1 <= s.length <= 105
 * - s consists of '(' and ')' only.
 *
 * Topics: String, Stack, Greedy, Bracket Sequences
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public int minInsertions(String s) {
        int ans = 0, x = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                ++x;
            } else {
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
}
