"""
LeetCode Problem 3121: Count the Number of Special Characters II
Problem Number: 3121
Difficulty: Medium
Link: https://leetcode.com/problems/count-the-number-of-special-characters-ii/

You are given a string word. A letter c is called special if it appears both in lowercase 
and uppercase in word, and all occurrences of c in lowercase appear before the first 
occurrence of c in uppercase.

Return the number of special characters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation: The special characters are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation: No uppercase letters exist.

Example 3:
Input: word = "AbBCab"
Output: 2
Explanation: The special characters are 'a' and 'b'.

Constraints:
- 1 <= word.length <= 2 * 10^5
- word consists of only lowercase and uppercase English letters.

Topics: String, Hash Table
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - using fixed size arrays of length 26
"""

class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        n = len(word)
        last_lower = [-1] * 26
        first_upper = [n] * 26
        
        # Track last occurrence of each lowercase letter
        for i, ch in enumerate(word):
            if 'a' <= ch <= 'z':
                last_lower[ord(ch) - ord('a')] = i
        
        # Track first occurrence of each uppercase letter
        for i, ch in enumerate(word):
            if 'A' <= ch <= 'Z':
                idx = ord(ch) - ord('A')
                if first_upper[idx] == n:
                    first_upper[idx] = i
        
        # Count letters where last lowercase appears before first uppercase
        count = 0
        for i in range(26):
            if last_lower[i] != -1 and first_upper[i] != n and last_lower[i] < first_upper[i]:
                count += 1
        
        return count
