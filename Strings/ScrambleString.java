/*
 * LeetCode Problem 87: Scramble String
 * Problem Number: 87
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/scramble-string/
 * 
 * We can scramble a string s to get a string t using the following algorithm:
 * 1. If the length of the string is 1, stop.
 * 2. If the length of the string is > 1, do the following:
 *    - Split the string into two non-empty substrings at a random index.
 *    - Optionally swap the two substrings or leave them in the original order.
 *    - Recursively scramble each substring.
 * 
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1.
 * 
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: "great" -> "gr/eat" -> "r/g/eat" -> "rg/eat" -> "rgeat"
 * 
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 * 
 * Example 3:
 * Input: s1 = "a", s2 = "a"
 * Output: true
 * 
 * Constraints:
 * - s1.length == s2.length
 * - 1 <= s1.length <= 30
 * - s1 and s2 consist of lowercase English letters.
 * 
 * Topics: String, Dynamic Programming, Memoization
 * Time Complexity: O(n^4) - with memoization, reduces to O(n^3) in practice
 * Space Complexity: O(n^3) - memoization cache size
 */

import java.util.Arrays;

class Solution {
    private Boolean[][][] memo;
    private String s1;
    private String s2;
    
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        // memo[i][j][k] = is substring starting at i in s1 and j in s2 of length k a scramble?
        memo = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }
    
    private boolean dfs(int i, int j, int k) {
        // Return cached result if available
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }
        
        // Base case: single character
        if (k == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        
        // Check character frequency match (optimization)
        int[] freq = new int[26];
        for (int idx = 0; idx < k; idx++) {
            freq[s1.charAt(i + idx) - 'a']++;
            freq[s2.charAt(j + idx) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                return memo[i][j][k] = false;
            }
        }
        
        // Try all possible split points
        for (int h = 1; h < k; h++) {
            // Case 1: No swap - both substrings stay in same order
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return memo[i][j][k] = true;
            }
            // Case 2: Swap - left part of s1 with right part of s2
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return memo[i][j][k] = true;
            }
        }
        
        return memo[i][j][k] = false;
    }
}
