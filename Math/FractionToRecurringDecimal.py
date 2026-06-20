"""
LeetCode Problem 166: Fraction to Recurring Decimal
Problem Number: 166
Difficulty: Medium
Link: https://leetcode.com/problems/fraction-to-recurring-decimal/

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 4, denominator = 333
Output: "0.(012)"

Constraints:
- -2^31 <= numerator, denominator <= 2^31 - 1
- denominator != 0

Topics: Hash Table, Math, String
Time Complexity: O(k) - where k is the length of the recurring cycle
Space Complexity: O(k) - for the hash map and string builder
"""

class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        if numerator == 0:
            return "0"
        
        result = []
        
        # Handle negative sign
        if (numerator < 0) ^ (denominator < 0):
            result.append("-")
        
        # Use absolute values
        num = abs(numerator)
        den = abs(denominator)
        
        # Append integer part
        result.append(str(num // den))
        remainder = num % den
        
        if remainder == 0:
            return "".join(result)
        
        # Append decimal point
        result.append(".")
        
        # Map to store remainder and its position in the result
        remainder_map = {}
        
        while remainder != 0:
            # If remainder already seen, we have a repeating cycle
            if remainder in remainder_map:
                pos = remainder_map[remainder]
                result.insert(pos, "(")
                result.append(")")
                break
            
            # Store the position before adding the digit
            remainder_map[remainder] = len(result)
            
            # Long division step
            remainder *= 10
            result.append(str(remainder // den))
            remainder %= den
        
        return "".join(result)
