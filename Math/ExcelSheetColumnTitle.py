"""
LeetCode Problem 168: Excel Sheet Column Title
Problem Number: 168
Difficulty: Easy
Link: https://leetcode.com/problems/excel-sheet-column-title/

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

Excel column titles are:
A -> 1, B -> 2, ... Z -> 26, AA -> 27, AB -> 28, ...

Example 1:
Input: columnNumber = 1
Output: "A"

Example 2:
Input: columnNumber = 28
Output: "AB"

Example 3:
Input: columnNumber = 701
Output: "ZY"

Example 4:
Input: columnNumber = 2147483647
Output: "FXSHRXW"

Constraints:
- 1 <= columnNumber <= 2^31 - 1

Topics: Math, String
Time Complexity: O(log(columnNumber)) - number of digits in the result
Space Complexity: O(log(columnNumber)) - for the result string
"""

class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        result = []
        
        while columnNumber > 0:
            # Convert to 0-based indexing by decrementing
            columnNumber -= 1
            # Get the remainder (0-25) and convert to 'A' - 'Z'
            result.append(chr(ord('A') + columnNumber % 26))
            # Move to next digit
            columnNumber //= 26
        
        # Reverse since we built from least significant to most significant
        return ''.join(reversed(result))
