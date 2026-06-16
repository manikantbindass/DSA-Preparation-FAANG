"""
LeetCode Problem 150: Evaluate Reverse Polish Notation
Problem Number: 150
Difficulty: Medium
Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/

You are given an array of strings tokens that represents an arithmetic expression
in Reverse Polish Notation. Evaluate the expression. Return an integer that represents the value.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22

Constraints:
- 1 <= tokens.length <= 10^4
- tokens[i] is either an operator: "+", "-", "*", "/", or an integer in the range [-200, 200].

Topics: Array, Math, Stack
Time Complexity: O(n) - where n is the number of tokens
Space Complexity: O(n) - for the stack
"""

from typing import List

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        operators = {'+', '-', '*', '/'}
        
        for token in tokens:
            if token not in operators:
                # Token is a number
                stack.append(int(token))
            else:
                # Token is an operator
                operand2 = stack.pop()
                operand1 = stack.pop()
                
                if token == '+':
                    result = operand1 + operand2
                elif token == '-':
                    result = operand1 - operand2
                elif token == '*':
                    result = operand1 * operand2
                else:  # token == '/'
                    # Integer division truncates toward zero
                    result = int(operand1 / operand2)
                
                stack.append(result)
        
        return stack[0]
