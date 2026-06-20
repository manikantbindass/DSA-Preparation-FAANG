/*
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
*/

package math

import "strconv"

func fractionToDecimal(numerator int, denominator int) string {
    if numerator == 0 {
        return "0"
    }
    
    result := []byte{}
    
    // Handle negative sign
    if (numerator < 0) != (denominator < 0) {
        result = append(result, '-')
    }
    
    // Use int64 to avoid overflow
    num := int64(numerator)
    den := int64(denominator)
    if num < 0 {
        num = -num
    }
    if den < 0 {
        den = -den
    }
    
    // Append integer part
    result = append(result, strconv.FormatInt(num/den, 10)...)
    remainder := num % den
    
    if remainder == 0 {
        return string(result)
    }
    
    // Append decimal point
    result = append(result, '.')
    
    // Map to store remainder and its position in the result
    remainderMap := make(map[int64]int)
    
    for remainder != 0 {
        // If remainder already seen, we have a repeating cycle
        if pos, exists := remainderMap[remainder]; exists {
            // Insert '(' at the position
            result = append(result[:pos], append([]byte{'('}, result[pos:]...)...)
            result = append(result, ')')
            break
        }
        
        // Store the position before adding the digit
        remainderMap[remainder] = len(result)
        
        // Long division step
        remainder *= 10
        result = append(result, strconv.FormatInt(remainder/den, 10)...)
        remainder %= den
    }
    
    return string(result)
}
