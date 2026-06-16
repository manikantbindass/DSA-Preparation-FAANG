/*
 * LeetCode Problem 150: Evaluate Reverse Polish Notation
 * Problem Number: 150
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 * You are given an array of strings tokens that represents an arithmetic expression
 * in Reverse Polish Notation. Evaluate the expression. Return an integer that represents the value.
 * 
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * 
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * 
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * 
 * Constraints:
 * - 1 <= tokens.length <= 10^4
 * - tokens[i] is either an operator: "+", "-", "*", "/", or an integer in the range [-200, 200].
 * 
 * Topics: Array, Math, Stack
 * Time Complexity: O(n) - where n is the number of tokens
 * Space Complexity: O(n) - for the stack
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (String token : tokens) {
            // Check if token is a number (including negative numbers)
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                // Token is an operator
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = applyOperator(operand1, operand2, token);
                stack.push(result);
            }
        }
        
        return stack.pop();
    }
    
    private boolean isNumber(String token) {
        // A token is a number if it's not an operator
        // This handles negative numbers like "-11"
        return !token.equals("+") && !token.equals("-") && 
               !token.equals("*") && !token.equals("/");
    }
    
    private int applyOperator(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b; // Integer division truncates toward zero
            default: return 0;
        }
    }
}
