"""
LeetCode Problem 131: Palindrome Partitioning
Problem Number: 131
Difficulty: Medium
Link: https://leetcode.com/problems/palindrome-partitioning/

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
- 1 <= s.length <= 16
- s consists of lowercase English letters only.

Topics: String, Dynamic Programming, Backtracking
Time Complexity: O(n * 2^n) - for generating all partitions
Space Complexity: O(n^2) - for DP table and recursion stack
"""

from typing import List

class Solution:
    def partition(self, s: str) -> List[List[str]]:
        n = len(s)
        # Precompute palindrome table using DP
        is_palindrome = [[True] * n for _ in range(n)]
        
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                is_palindrome[i][j] = (s[i] == s[j]) and is_palindrome[i + 1][j - 1]
        
        result = []
        current = []
        
        def dfs(start: int):
            if start == n:
                result.append(current.copy())
                return
            
            for end in range(start, n):
                if is_palindrome[start][end]:
                    current.append(s[start:end + 1])
                    dfs(end + 1)
                    current.pop()
        
        dfs(0)
        return result
