"""
LeetCode Problem 172: Factorial Trailing Zeroes
Problem Number: 172
Difficulty: Medium
Link: https://leetcode.com/problems/factorial-trailing-zeroes/

Given an integer n, return the number of trailing zeroes in n!.

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Example 3:
Input: n = 0
Output: 0

Constraints:
- 0 <= n <= 10^4

Topics: Math
Time Complexity: O(log n) - number of divisions by 5
Space Complexity: O(1) - only using constant extra space
"""

class Solution:
    def trailingZeroes(self, n: int) -> int:
        count = 0
        while n > 0:
            n //= 5
            count += n
        return count
