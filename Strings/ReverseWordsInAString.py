"""
LeetCode Problem 151: Reverse Words in a String
Problem Number: 151
Difficulty: Medium
Link: https://leetcode.com/problems/reverse-words-in-a-string/

Given an input string s, reverse the order of the words. A word is defined as a sequence
of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space.

Constraints:
- 1 <= s.length <= 10^4
- s contains English letters (upper-case and lower-case), digits, and spaces ' '.
- There is at least one word in s.

Topics: Two Pointers, String
Time Complexity: O(n) - where n is the length of the string
Space Complexity: O(n) - for storing the words
"""

class Solution:
    def reverseWords(self, s: str) -> str:
        # Split by spaces and filter out empty strings
        words = s.split()
        # Reverse the list and join with a single space
        return ' '.join(words[::-1])
