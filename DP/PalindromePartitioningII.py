"""
LeetCode Problem 132: Palindrome Partitioning II
Problem Number: 132
Difficulty: Hard
Link: https://leetcode.com/problems/palindrome-partitioning-ii/

Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

Example 2:
Input: s = "a"
Output: 0

Example 3:
Input: s = "ab"
Output: 1

Constraints:
- 1 <= s.length <= 2000
- s consists of lowercase English letters only.

Topics: String, Dynamic Programming
Time Complexity: O(n^2) - where n is the length of the string
Space Complexity: O(n^2) - for the palindrome DP table
"""

class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        # dp[i][j] = True if substring s[i..j] is palindrome
        is_palindrome = [[True] * n for _ in range(n)]
        
        # Build palindrome table using DP
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                is_palindrome[i][j] = (s[i] == s[j]) and is_palindrome[i + 1][j - 1]
        
        # cuts[i] = minimum cuts needed for substring s[0..i]
        cuts = [i for i in range(n)]  # Maximum cuts needed (cut after each character)
        
        for i in range(1, n):
            for j in range(i + 1):
                if is_palindrome[j][i]:
                    if j == 0:
                        cuts[i] = 0  # Whole substring is palindrome
                    else:
                        cuts[i] = min(cuts[i], 1 + cuts[j - 1])
        
        return cuts[n - 1]
