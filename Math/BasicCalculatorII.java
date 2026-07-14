/*
 * LeetCode Problem 227: Basic Calculator II
 * Problem Number: 227
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/basic-calculator-ii/
 *
 * Given a string s which represents an expression, evaluate this expression and
 * return its value. 
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate
 * results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings
 * as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 3 * 105
 * 	s consists of integers and operators ('+', '-', '*', '/') separated by some
 * number of spaces.
 * 	s represents a valid expression.
 * 	All the integers in the expression are non-negative integers in the range [0,
 * 231 - 1].
 * 	The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: s = "3+2*2"
 * Output: 7
 *
 * Example 2:
 * Input: s = " 3/2 "
 * Output: 1
 *
 * Example 3:
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 * Constraints:
 * - 1 <= s.length <= 3 * 105
 * - s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * - s represents
 *
 * Topics: Math, String, Stack
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign ='+';
        int v = 0;
        for (int i = 0; i < s.length();++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                v = v * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c== '+' || c == '-' || c == '*' || c == '/'){
                if (sign == '+') {
                    stk.push(v);
                } else if (sign == '-') {
                    stk.push(-v);
                } else if (sign =='*') {
                    stk.push(stk.pop() * v);
                } else {
                    stk.push(stk.pop() / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.isEmpty()) {
            ans +=stk.pop();
        }
        return ans;
    }
}
