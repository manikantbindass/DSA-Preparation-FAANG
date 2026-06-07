/*
 * LeetCode Problem 132: Palindrome Partitioning II
 * Problem Number: 132
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/palindrome-partitioning-ii/
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * Example 1:
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * Example 2:
 * Input: s = "a"
 * Output: 0
 * 
 * Example 3:
 * Input: s = "ab"
 * Output: 1
 * 
 * Constraints:
 * - 1 <= s.length <= 2000
 * - s consists of lowercase English letters only.
 * 
 * Topics: String, Dynamic Programming
 * Time Complexity: O(n^2) - where n is the length of the string
 * Space Complexity: O(n^2) - for the palindrome DP table
 */

import java.util.Arrays;

class Solution {
    public int minCut(String s) {
        int n = s.length();
        // dp[i][j] = true if substring s[i..j] is palindrome
        boolean[][] isPalindrome = new boolean[n][n];
        
        // Initialize all single characters as palindromes
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPalindrome[i], true);
        }
        
        // Build palindrome table using DP
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }
        
        // dp[i] = minimum cuts needed for substring s[0..i]
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i; // Maximum cuts needed (cut after each character)
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) {
                    if (j == 0) {
                        dp[i] = 0; // Whole substring is palindrome
                    } else {
                        dp[i] = Math.min(dp[i], 1 + dp[j - 1]);
                    }
                }
            }
        }
        
        return dp[n - 1];
    }
}
