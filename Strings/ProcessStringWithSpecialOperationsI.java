/*
 * LeetCode Problem 3612: Process String with Special Operations I
 * Problem Number: 3612
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/process-string-with-special-operations-i/
 * 
 * You are given a string s. Process the string with the following operations:
 * - If the character is a letter, append it to the result.
 * - If the character is '*', delete the last character from the result.
 * - If the character is '#', duplicate the current result.
 * - If the character is '%', reverse the current result.
 * 
 * Return the final result string.
 * 
 * Example:
 * Input: s = "abc*def#%"
 * Output: "defdef"
 * 
 * Constraints:
 * - 1 <= s.length <= 100
 * - s consists of lowercase English letters and the characters '*', '#', '%'.
 * 
 * Topics: String, Simulation
 * Time Complexity: O(n) - where n is the length of the string
 * Space Complexity: O(n) - for the result string
 */

class Solution {
    public String processStr(String s) {
        StringBuilder result = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                // Append letters to the result
                result.append(c);
            } else if (c == '*') {
                // Delete the last character if the result is not empty
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else if (c == '#') {
                // Duplicate the current result
                String current = result.toString();
                result.append(current);
            } else if (c == '%') {
                // Reverse the current result
                result.reverse();
            }
        }
        
        return result.toString();
    }
}
