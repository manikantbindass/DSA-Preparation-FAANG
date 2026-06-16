/*
LeetCode Problem 3612: Process String with Special Operations I
Problem Number: 3612
Difficulty: Easy
Link: https://leetcode.com/problems/process-string-with-special-operations-i/

You are given a string s. Process the string with the following operations:
- If the character is a letter, append it to the result.
- If the character is '*', delete the last character from the result.
- If the character is '#', duplicate the current result.
- If the character is '%', reverse the current result.

Return the final result string.

Example:
Input: s = "abc*def#%"
Output: "defdef"

Constraints:
- 1 <= s.length <= 100
- s consists of lowercase English letters and the characters '*', '#', '%'.

Topics: String, Simulation
Time Complexity: O(n) - where n is the length of the string
Space Complexity: O(n) - for the result string
*/

package strings

import "unicode"

func processStr(s string) string {
    result := make([]rune, 0)
    
    for _, ch := range s {
        if unicode.IsLetter(ch) {
            // Append letters to the result
            result = append(result, ch)
        } else if ch == '*' {
            // Delete the last character if the result is not empty
            if len(result) > 0 {
                result = result[:len(result)-1]
            }
        } else if ch == '#' {
            // Duplicate the current result
            result = append(result, result...)
        } else if ch == '%' {
            // Reverse the current result
            for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
                result[i], result[j] = result[j], result[i]
            }
        }
    }
    
    return string(result)
}
