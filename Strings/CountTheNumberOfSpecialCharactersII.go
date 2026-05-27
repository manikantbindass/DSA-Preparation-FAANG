/*
LeetCode Problem 3121: Count the Number of Special Characters II
Problem Number: 3121
Difficulty: Medium
Link: https://leetcode.com/problems/count-the-number-of-special-characters-ii/

You are given a string word. A letter c is called special if it appears both in lowercase 
and uppercase in word, and all occurrences of c in lowercase appear before the first 
occurrence of c in uppercase.

Return the number of special characters in word.

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
- 1 <= word.length <= 2 * 10^5
- word consists of only lowercase and uppercase English letters.

Topics: String, Hash Table
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - using fixed size arrays of length 26
*/

package strings

func numberOfSpecialChars(word string) int {
    n := len(word)
    lastLower := make([]int, 26)
    firstUpper := make([]int, 26)
    
    // Initialize arrays with appropriate sentinel values
    for i := 0; i < 26; i++ {
        lastLower[i] = -1
        firstUpper[i] = n
    }
    
    // Track last occurrence of each lowercase letter
    for i := 0; i < n; i++ {
        ch := word[i]
        if ch >= 'a' && ch <= 'z' {
            lastLower[ch-'a'] = i
        }
    }
    
    // Track first occurrence of each uppercase letter
    for i := 0; i < n; i++ {
        ch := word[i]
        if ch >= 'A' && ch <= 'Z' {
            idx := ch - 'A'
            if firstUpper[idx] == n {
                firstUpper[idx] = i
            }
        }
    }
    
    // Count letters where last lowercase appears before first uppercase
    count := 0
    for i := 0; i < 26; i++ {
        if lastLower[i] != -1 && firstUpper[i] != n && lastLower[i] < firstUpper[i] {
            count++
        }
    }
    
    return count
}
