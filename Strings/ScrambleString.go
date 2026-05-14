/*
LeetCode Problem 87: Scramble String
Problem Number: 87
Difficulty: Hard
Link: https://leetcode.com/problems/scramble-string/

We can scramble a string s to get a string t using the following algorithm:
1. If the length of the string is 1, stop.
2. If the length of the string is > 1, do the following:
   - Split the string into two non-empty substrings at a random index.
   - Optionally swap the two substrings or leave them in the original order.
   - Recursively scramble each substring.

Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1.

Example 1:
Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: "great" -> "gr/eat" -> "r/g/eat" -> "rg/eat" -> "rgeat"

Example 2:
Input: s1 = "abcde", s2 = "caebd"
Output: false

Example 3:
Input: s1 = "a", s2 = "a"
Output: true

Constraints:
- s1.length == s2.length
- 1 <= s1.length <= 30
- s1 and s2 consist of lowercase English letters.

Topics: String, Dynamic Programming, Memoization
Time Complexity: O(n^4) - with memoization, reduces to O(n^3) in practice
Space Complexity: O(n^3) - memoization cache size
*/

package strings

func isScramble(s1 string, s2 string) bool {
    n := len(s1)
    if n != len(s2) {
        return false
    }
    
    // memo[i][j][k] = is substring starting at i in s1 and j in s2 of length k a scramble?
    memo := make([][][]*bool, n)
    for i := 0; i < n; i++ {
        memo[i] = make([][]*bool, n)
        for j := 0; j < n; j++ {
            memo[i][j] = make([]*bool, n+1)
        }
    }
    
    var dfs func(i, j, k int) bool
    dfs = func(i, j, k int) bool {
        if memo[i][j][k] != nil {
            return *memo[i][j][k]
        }
        
        // Base case: single character
        if k == 1 {
            result := s1[i] == s2[j]
            memo[i][j][k] = &result
            return result
        }
        
        // Check character frequency match (optimization)
        freq := make([]int, 26)
        for idx := 0; idx < k; idx++ {
            freq[s1[i+idx]-'a']++
            freq[s2[j+idx]-'a']--
        }
        for _, count := range freq {
            if count != 0 {
                result := false
                memo[i][j][k] = &result
                return false
            }
        }
        
        // Try all possible split points
        for h := 1; h < k; h++ {
            // Case 1: No swap - both substrings stay in same order
            if dfs(i, j, h) && dfs(i+h, j+h, k-h) {
                result := true
                memo[i][j][k] = &result
                return true
            }
            // Case 2: Swap - left part of s1 with right part of s2
            if dfs(i+h, j, k-h) && dfs(i, j+k-h, h) {
                result := true
                memo[i][j][k] = &result
                return true
            }
        }
        
        result := false
        memo[i][j][k] = &result
        return false
    }
    
    return dfs(0, 0, n)
}
