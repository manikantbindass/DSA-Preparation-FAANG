"""
LeetCode Problem 1291: Sequential Digits
Problem Number: 1291
Difficulty: Medium
Link: https://leetcode.com/problems/sequential-digits/

An integer has sequential digits if and only if each digit in the number is one more
than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
- 10 <= low <= high <= 10^9

Topics: Math, Enumeration, Backtracking
Time Complexity: O(1) - constant number of sequential digit numbers (only 45 possible)
Space Complexity: O(1) - excluding the output list
"""

from typing import List

class Solution:
    def sequentialDigits(self, low: int, high: int) -> List[int]:
        result = []
        
        # Start from each possible first digit (1 to 9)
        for start in range(1, 10):
            num = start
            # Build numbers by appending consecutive digits
            for next_digit in range(start + 1, 10):
                num = num * 10 + next_digit
                if low <= num <= high:
                    result.append(num)
        
        return sorted(result)
