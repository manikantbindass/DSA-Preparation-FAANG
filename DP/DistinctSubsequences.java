/*
 * LeetCode Problem 115: Distinct Subsequences
 * Problem Number: 115
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/distinct-subsequences/
 * 
 * Given two strings s and t, return the number of distinct subsequences of s which equal t.
 * 
 * Example 1:
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation: There are 3 ways to get "rabbit" from "rabbbit":
 *   rabb b it
 *   rab b bit
 *   rab b bit
 * 
 * Example 2:
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * 
 * Constraints:
 * - 0 <= s.length, t.length <= 1000
 * - s and t consist of English letters.
 * 
 * Topics: String, Dynamic Programming
 * Time Complexity: O(m * n) - where m and n are lengths of s and t
 * Space Complexity: O(m * n) - for the DP table (can be optimized to O(n))
 */

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[i][j] = number of distinct subsequences of s[0..i-1] that equal t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Empty string t is a subsequence of any s (1 way)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Case 1: Exclude current character of s
                dp[i][j] = dp[i - 1][j];
                
                // Case 2: If characters match, include current character of s
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[m][n];
    }
}
