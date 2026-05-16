/*
LeetCode Problem 72: Edit Distance
Problem Number: 72
Difficulty: Hard
Link: https://leetcode.com/problems/edit-distance/

Given two strings word1 and word2, return the minimum number of operations 
required to convert word1 to word2.

You have the following three operations permitted on a word:
- Insert a character
- Delete a character
- Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:
- 0 <= word1.length, word2.length <= 500
- word1 and word2 consist of lowercase English letters.

Topics: String, Dynamic Programming
Time Complexity: O(m * n) - where m and n are lengths of the two strings
Space Complexity: O(m * n) - for the DP table
*/

package dp

func minDistance(word1 string, word2 string) int {
    m, n := len(word1), len(word2)
    
    // Create DP table
    dp := make([][]int, m+1)
    for i := range dp {
        dp[i] = make([]int, n+1)
    }
    
    // Initialize base cases
    for j := 1; j <= n; j++ {
        dp[0][j] = j  // Insert all characters of word2
    }
    for i := 1; i <= m; i++ {
        dp[i][0] = i  // Delete all characters of word1
    }
    
    // Fill the DP table
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if word1[i-1] == word2[j-1] {
                dp[i][j] = dp[i-1][j-1]  // Characters match
            } else {
                dp[i][j] = min(dp[i-1][j], min(dp[i][j-1], dp[i-1][j-1])) + 1
            }
        }
    }
    
    return dp[m][n]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
