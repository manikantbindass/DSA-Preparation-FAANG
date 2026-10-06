/*
 * LeetCode Problem 957: Minimum Add to Make Parentheses Valid
 * Problem Number: 957
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 *
 * A parentheses string is valid if and only if:
 *
 * 	It is the empty string,
 * 	It can be written as AB (A concatenated with B), where A and B are valid
 * strings, or
 * 	It can be written as (A), where A is a valid string.
 *
 * You are given a parentheses string s. In one move, you can insert a parenthesis
 * at any position of the string.
 *
 * 	For example, if s = "()))", you can insert an opening parenthesis to be "(()))"
 * or a closing parenthesis to be "())))".
 *
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 *
 * Example 2:
 *
 * Input: s = "((("
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 1000
 * 	s[i] is either '(' or ')'.
 *
 * Example 1:
 * Input: s = "())"
 * Output: 1
 *
 * Example 2:
 * Input: s = "((("
 * Output: 3
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s[i] is either '(' or ')'.
 *
 * Topics: String, Stack, Greedy, Bracket Sequences
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !stk.isEmpty() && stk.peek() == '(') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.size();
    }
}
