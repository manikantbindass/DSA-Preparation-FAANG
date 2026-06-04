/*
 * LeetCode Problem 3751: Total Waviness of Numbers in Range I
 * Problem Number: 3751
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/
 * 
 * The waviness of a number is defined as the count of its digits that are either:
 * - Strictly greater than both adjacent digits (peak), or
 * - Strictly less than both adjacent digits (valley)
 * 
 * Given two integers num1 and num2, return the total waviness of all numbers
 * in the inclusive range [num1, num2].
 * 
 * Example 1:
 * Input: num1 = 10, num2 = 50
 * Output: 38
 * 
 * Example 2:
 * Input: num1 = 1, num2 = 5
 * Output: 0
 * Explanation: Single-digit numbers have no waviness.
 * 
 * Constraints:
 * - 1 <= num1 <= num2 <= 10^5
 * 
 * Topics: Math, String, Enumeration
 * Time Complexity: O((num2 - num1 + 1) * L) where L is the number of digits
 * Space Complexity: O(1) - excluding the space for string conversion
 */

class Solution {
    /**
     * Calculates the waviness of a single number.
     * Waviness is the count of digits that are peaks or valleys.
     * 
     * @param n The number to analyze
     * @return The waviness count
     */
    public int waviness(int n) {
        String s = Integer.toString(n);
        int count = 0;
        
        // Check each interior digit (cannot be first or last)
        for (int i = 1; i < s.length() - 1; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);
            char next = s.charAt(i + 1);
            
            // Check if current digit is a peak (greater than both neighbors)
            if (curr > prev && curr > next) {
                count++;
            }
            // Check if current digit is a valley (less than both neighbors)
            else if (curr < prev && curr < next) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Calculates the total waviness for all numbers in the range [num1, num2].
     * 
     * @param num1 The start of the range (inclusive)
     * @param num2 The end of the range (inclusive)
     * @return The total waviness sum
     */
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        for (int i = num1; i <= num2; i++) {
            total += waviness(i);
        }
        return total;
    }
}
