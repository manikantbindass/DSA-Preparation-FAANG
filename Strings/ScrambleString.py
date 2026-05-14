"""
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
"""

from functools import lru_cache

class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        
        @lru_cache(maxsize=None)
        def dfs(i: int, j: int, k: int) -> bool:
            """
            Check if s1[i:i+k] is scramble of s2[j:j+k]
            """
            # Base case: single character
            if k == 1:
                return s1[i] == s2[j]
            
            # Quick check: character frequency must match
            if sorted(s1[i:i+k]) != sorted(s2[j:j+k]):
                return False
            
            # Try all possible split points
            for h in range(1, k):
                # Case 1: No swap - both substrings stay in same order
                if dfs(i, j, h) and dfs(i + h, j + h, k - h):
                    return True
                # Case 2: Swap - left part of s1 with right part of s2
                if dfs(i + h, j, k - h) and dfs(i, j + k - h, h):
                    return True
            
            return False
        
        return dfs(0, 0, n)
