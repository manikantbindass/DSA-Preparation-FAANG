/*
 * LeetCode Problem 224: Basic Calculator
 * Problem Number: 224
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/basic-calculator/
 *
 * Given a string s representing a valid expression, implement a basic calculator
 * to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings
 * as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 3 * 105
 * 	s consists of digits, '+', '-', '(', ')', and ' '.
 * 	s represents a valid expression.
 * 	'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * 	'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * 	There will be no two consecutive operators in the input.
 * 	Every number and running calculation will fit in a signed 32-bit integer.
 *
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 * - 1 <= s.length <= 3 * 105
 * - s consists of digits, '+', '-', '(', ')', and ' '.
 * - s represents a valid expression.
 * - '+' is
 *
 * Topics: Math, String, Stack, Recursion
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        int sign = 1;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                int x = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    x = x * 10 + s.charAt(j) - '0';
                    j++;
                }
                ans += sign * x;
                i = j - 1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (c == ')') {
                ans = stk.pop() * ans + stk.pop();
            }
        }
        return ans;
    }
}
