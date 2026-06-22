"""
LeetCode Problem 171: Excel Sheet Column Number
Problem Number: 171
Difficulty: Easy
Link: https://leetcode.com/problems/excel-sheet-column-number/

Given a string columnTitle that represents the column title as appears in an Excel sheet,
return its corresponding column number.

For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 

Example 1:
Input: columnTitle = "A"
Output: 1

Example 2:
Input: columnTitle = "AB"
Output: 28

Example 3:
Input: columnTitle = "ZY"
Output: 701

Constraints:
- 1 <= columnTitle.length <= 7
- columnTitle consists only of uppercase English letters.

Topics: Math, String
Time Complexity: O(n) - where n is the length of the string
Space Complexity: O(1) - only using constant extra space
"""

class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        result = 0
        for char in columnTitle:
            digit_value = ord(char) - ord('A') + 1
            result = result * 26 + digit_value
        return result
