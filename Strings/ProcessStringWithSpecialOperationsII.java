/*
 * LeetCode Problem 3614: Process String with Special Operations II
 * Problem Number: 3614
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/process-string-with-special-operations-ii/
 * 
 * Given a string s containing letters and special characters ('*', '#', '%'), and a long integer k,
 * process the string and return the k-th character after applying operations.
 * 
 * Example:
 * Input: s = "a#b*", k = 1
 * Output: 'a'
 * 
 * Constraints:
 * - 1 <= s.length <= 1000
 * - 0 <= k <= 10^18
 * 
 * Topics: String, Simulation
 * Time Complexity: O(n) - where n is the length of the string
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public char processStr(String s, long k) {
        long m = 0;
        
        // First pass: process the string to determine the total length
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                // Delete the last character if possible
                m = Math.max(0, m - 1);
            } else if (c == '#') {
                // Duplicate the entire string (multiply by 2)
                m <<= 1;
            } else if (c != '%') {
                // Add a regular character
                m += 1;
            }
            // '%' character is a placeholder that doesn't affect length directly
        }
        
        // If k is out of bounds, return '.'
        if (k >= m) {
            return '.';
        }
        
        // Second pass: process from right to left to find the k-th character
        for (int i = s.length() - 1;; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                // Undo deletion: increase m by 1
                m += 1;
            } else if (c == '#') {
                // Undo duplication: halve m
                m /= 2;
                if (k >= m) {
                    k -= m;
                }
            } else if (c == '%') {
                // Reverse the order of characters
                k = m - 1 - k;
            } else {
                // Regular character: decrease m by 1
                m -= 1;
                if (k == m) {
                    return c;
                }
            }
        }
    }
}
