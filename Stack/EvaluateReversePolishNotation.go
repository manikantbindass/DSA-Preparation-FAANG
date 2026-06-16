/*
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
*/

package stack

import "strconv"

func evalRPN(tokens []string) int {
    stack := make([]int, 0)
    
    for _, token := range tokens {
        switch token {
        case "+":
            operand2 := stack[len(stack)-1]
            operand1 := stack[len(stack)-2]
            stack = stack[:len(stack)-2]
            stack = append(stack, operand1+operand2)
        case "-":
            operand2 := stack[len(stack)-1]
            operand1 := stack[len(stack)-2]
            stack = stack[:len(stack)-2]
            stack = append(stack, operand1-operand2)
        case "*":
            operand2 := stack[len(stack)-1]
            operand1 := stack[len(stack)-2]
            stack = stack[:len(stack)-2]
            stack = append(stack, operand1*operand2)
        case "/":
            operand2 := stack[len(stack)-1]
            operand1 := stack[len(stack)-2]
            stack = stack[:len(stack)-2]
            // Integer division truncates toward zero
            stack = append(stack, operand1/operand2)
        default:
            // Token is a number
            num, _ := strconv.Atoi(token)
            stack = append(stack, num)
        }
    }
    
    return stack[0]
}
