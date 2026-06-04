"""
LeetCode Problem 3751: Total Waviness of Numbers in Range I
Problem Number: 3751
Difficulty: Easy
Link: https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/

The waviness of a number is defined as the count of its digits that are either:
- Strictly greater than both adjacent digits (peak), or
- Strictly less than both adjacent digits (valley)

Given two integers num1 and num2, return the total waviness of all numbers
in the inclusive range [num1, num2].

Example 1:
Input: num1 = 10, num2 = 50
Output: 38

Example 2:
Input: num1 = 1, num2 = 5
Output: 0
Explanation: Single-digit numbers have no waviness.

Constraints:
- 1 <= num1 <= num2 <= 10^5

Topics: Math, String, Enumeration
Time Complexity: O((num2 - num1 + 1) * L) where L is the number of digits
Space Complexity: O(1) - excluding the space for string conversion
"""

class Solution:
    def waviness(self, n: int) -> int:
        """
        Calculates the waviness of a single number.
        Waviness is the count of digits that are peaks or valleys.
        
        Args:
            n: The number to analyze
            
        Returns:
            The waviness count
        """
        s = str(n)
        count = 0
        
        # Check each interior digit (cannot be first or last)
        for i in range(1, len(s) - 1):
            curr = s[i]
            prev = s[i - 1]
            nxt = s[i + 1]
            
            # Check if current digit is a peak (greater than both neighbors)
            if curr > prev and curr > nxt:
                count += 1
            # Check if current digit is a valley (less than both neighbors)
            elif curr < prev and curr < nxt:
                count += 1
        
        return count
    
    def totalWaviness(self, num1: int, num2: int) -> int:
        """
        Calculates the total waviness for all numbers in the range [num1, num2].
        
        Args:
            num1: The start of the range (inclusive)
            num2: The end of the range (inclusive)
            
        Returns:
            The total waviness sum
        """
        total = 0
        for i in range(num1, num2 + 1):
            total += self.waviness(i)
        return total
