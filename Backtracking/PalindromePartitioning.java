/*
 * LeetCode Problem 131: Palindrome Partitioning
 * Problem Number: 131
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/palindrome-partitioning/
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * 
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * 
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 * 
 * Constraints:
 * - 1 <= s.length <= 16
 * - s consists of lowercase English letters only.
 * 
 * Topics: String, Dynamic Programming, Backtracking
 * Time Complexity: O(n * 2^n) - for generating all partitions
 * Space Complexity: O(n^2) - for DP table and recursion stack
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int n;
    private String s;
    private boolean[][] isPalindrome;
    private List<String> current = new ArrayList<>();
    private List<List<String>> result = new ArrayList<>();
    
    public List<List<String>> partition(String s) {
        this.s = s;
        this.n = s.length();
        this.isPalindrome = new boolean[n][n];
        
        // Precompute palindrome table using DP
        // Initialize all single characters as palindromes
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPalindrome[i], true);
        }
        
        // Build palindrome table: isPalindrome[i][j] is true if s[i..j] is palindrome
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }
        
        dfs(0);
        return result;
    }
    
    private void dfs(int start) {
        if (start == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int end = start; end < n; end++) {
            if (isPalindrome[start][end]) {
                current.add(s.substring(start, end + 1));
                dfs(end + 1);
                current.remove(current.size() - 1);
            }
        }
    }
}
