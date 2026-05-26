/*
LeetCode Problem 3120: Count the Number of Special Characters I
Problem Number: 3120
Difficulty: Easy
Link: https://leetcode.com/problems/count-the-number-of-special-characters-i/

You are given a string word. A letter c is called special if it appears both in lowercase 
and uppercase in word, and all occurrences of c in lowercase appear before the first 
occurrence of c in uppercase.

Return the number of special characters in word.

Note: This is the simplified version where we only need to count characters that have both 
lowercase and uppercase versions in the string (regardless of order for this version).

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation: The special characters are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation: No uppercase letters exist.

Example 3:
Input: word = "AbBCab"
Output: 2
Explanation: The special characters are 'a' and 'b'.

Constraints:
- 1 <= word.length <= 50
- word consists of only lowercase and uppercase English letters.

Topics: String, Hash Table
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - using fixed size array of 128 (ASCII)
*/

package strings

func numberOfSpecialChars(word string) int {
    // Use a boolean array to mark seen characters
    seen := make([]bool, 128) // ASCII range
    
    // Mark all characters present in the word
    for i := 0; i < len(word); i++ {
        seen[word[i]] = true
    }
    
    count := 0
    // Check for each letter if both lowercase and uppercase exist
    for i := 0; i < 26; i++ {
        if seen['a'+byte(i)] && seen['A'+byte(i)] {
            count++
        }
    }
    
    return count
}
