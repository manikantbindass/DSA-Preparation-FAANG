"""
LeetCode Problem 1189: Maximum Number of Balloons
Problem Number: 1189
Difficulty: Easy
Link: https://leetcode.com/problems/maximum-number-of-balloons/

Given a string text, you want to use the characters of text to form as many instances
of the word "balloon" as possible. You can use each character in text at most once.

Return the maximum number of instances that can be formed.

Example 1:
Input: text = "nlaebolko"
Output: 1
Explanation: "nlaebolko" contains 1 "balloon" ("b", "a", "l", "l", "o", "o", "n").

Example 2:
Input: text = "loonbalxballpoon"
Output: 2
Explanation: "loonbalxballpoon" contains 2 "balloon" instances.

Example 3:
Input: text = "leetcode"
Output: 0

Constraints:
- 1 <= text.length <= 10^4
- text consists of lowercase English letters only.

Topics: Hash Table, String, Counting
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - using fixed size dictionary
"""

from collections import Counter

class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        # Count frequency of each character in text
        char_count = Counter(text)
        
        # The word "balloon" requires:
        # b: 1, a: 1, l: 2, o: 2, n: 1
        
        # Calculate maximum possible balloons
        max_balloons = float('inf')
        
        # Check required characters: b, a, l, o, n
        required = {'b': 1, 'a': 1, 'l': 2, 'o': 2, 'n': 1}
        
        for char, needed in required.items():
            # If character is missing, return 0
            if char not in char_count:
                return 0
            max_balloons = min(max_balloons, char_count[char] // needed)
        
        return max_balloons
