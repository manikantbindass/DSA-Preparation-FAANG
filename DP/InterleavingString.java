/*
 * LeetCode Problem 97: Interleaving String
 * Problem Number: 97
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/interleaving-string/
 * 
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * 
 * An interleaving of two strings s and t is a configuration where s and t are divided into
 * n non-empty substrings such that:
 * - s = s1 + s2 + ... + sn
 * - t = t1 + t2 + ... + tn
 * - The interleaving is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...
 * 
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to interleave is: "aa" (from s1) + "dbbc" (from s2) + "bc" (from s1) + "a" (from s2) + "c" (from s1)
 * 
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * 
 * Example 3:
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * 
 * Constraints:
 * - 0 <= s1.length, s2.length <= 100
 * - 0 <= s3.length <= 200
 * - s1, s2, and s3 consist of lowercase English letters.
 * 
 * Topics: String, Dynamic Programming
 * Time Complexity: O(m * n) - where m and n are lengths of s1 and s2
 * Space Complexity: O(m * n) - for memoization cache
 */

import java.util.HashMap;
import java.util.Map;
import java.util.List;

class Solution {
    private Map<List<Integer>, Boolean> memo = new HashMap<>();
    private String s1;
    private String s2;
    private String s3;
    private int m;
    private int n;
    
    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        
        // Early length check
        if (m + n != s3.length()) {
            return false;
        }
        
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        
        return dfs(0, 0);
    }
    
    private boolean dfs(int i, int j) {
        // Base case: reached end of both strings
        if (i >= m && j >= n) {
            return true;
        }
        
        List<Integer> key = List.of(i, j);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int k = i + j;
        boolean result = false;
        
        // Try taking a character from s1
        if (i < m && s1.charAt(i) == s3.charAt(k) && dfs(i + 1, j)) {
            result = true;
        }
        
        // Try taking a character from s2
        if (!result && j < n && s2.charAt(j) == s3.charAt(k) && dfs(i, j + 1)) {
            result = true;
        }
        
        memo.put(key, result);
        return result;
    }
}
