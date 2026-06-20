/*
 * LeetCode Problem 166: Fraction to Recurring Decimal
 * Problem Number: 166
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/fraction-to-recurring-decimal/
 * 
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * 
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * 
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * 
 * Example 3:
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 * 
 * Constraints:
 * - -2^31 <= numerator, denominator <= 2^31 - 1
 * - denominator != 0
 * 
 * Topics: Hash Table, Math, String
 * Time Complexity: O(k) - where k is the length of the recurring cycle
 * Space Complexity: O(k) - for the hash map and string builder
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder result = new StringBuilder();
        
        // Handle negative sign
        if ((numerator > 0) ^ (denominator > 0)) {
            result.append("-");
        }
        
        // Use long to avoid overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Append integer part
        result.append(num / den);
        long remainder = num % den;
        
        if (remainder == 0) {
            return result.toString();
        }
        
        // Append decimal point
        result.append(".");
        
        // Map to store remainder and its position in the result
        Map<Long, Integer> remainderMap = new HashMap<>();
        
        while (remainder != 0) {
            // If remainder already seen, we have a repeating cycle
            if (remainderMap.containsKey(remainder)) {
                int pos = remainderMap.get(remainder);
                result.insert(pos, "(");
                result.append(")");
                break;
            }
            
            // Store the position before adding the digit
            remainderMap.put(remainder, result.length());
            
            // Long division step
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        
        return result.toString();
    }
}
