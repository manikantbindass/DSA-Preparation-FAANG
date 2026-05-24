"""
LeetCode Problem 97: Interleaving String
Problem Number: 97
Difficulty: Medium
Link: https://leetcode.com/problems/interleaving-string/

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into
n non-empty substrings such that:
- s = s1 + s2 + ... + sn
- t = t1 + t2 + ... + tn
- The interleaving is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to interleave is: "aa" (from s1) + "dbbc" (from s2) + "bc" (from s1) + "a" (from s2) + "c" (from s1)

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true

Constraints:
- 0 <= s1.length, s2.length <= 100
- 0 <= s3.length <= 200
- s1, s2, and s3 consist of lowercase English letters.

Topics: String, Dynamic Programming
Time Complexity: O(m * n) - where m and n are lengths of s1 and s2
Space Complexity: O(m * n) - for memoization cache
"""

from functools import lru_cache

class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        
        # Early length check
        if m + n != len(s3):
            return False
        
        @lru_cache(maxsize=None)
        def dfs(i: int, j: int) -> bool:
            # Base case: reached end of both strings
            if i >= m and j >= n:
                return True
            
            k = i + j
            
            # Try taking a character from s1
            if i < m and s1[i] == s3[k] and dfs(i + 1, j):
                return True
            
            # Try taking a character from s2
            if j < n and s2[j] == s3[k] and dfs(i, j + 1):
                return True
            
            return False
        
        return dfs(0, 0)
