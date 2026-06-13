"""
LeetCode Problem 3838: Weighted Word Mapping
Problem Number: 3838
Difficulty: Easy
Link: https://leetcode.com/problems/weighted-word-mapping/

Given an array of words and an array of weights, map each word to a transformed character.
For each word, compute the sum of weights for its characters modulo 26,
then map it to the letter ('a' + (25 - s)) where s is the sum modulo 26.

Example:
Input: words = ["abc","def"], weights = [1,2,3,4,5,6]
Output: "zx" (example)

Constraints:
- 1 <= words.length <= 1000
- 1 <= words[i].length <= 100
- weights.length == 26
- 0 <= weights[i] <= 10^9

Topics: String, Hash Table, Math
Time Complexity: O(L) - where L is the total length of all words
Space Complexity: O(n) - for the result string
"""

from typing import List

class Solution:
    def mapWordWeights(self, words: List[str], weights: List[int]) -> str:
        result = []
        
        for word in words:
            total = 0
            # Compute weighted sum for the word
            for ch in word:
                idx = ord(ch) - ord('a')
                total = (total + weights[idx]) % 26
            # Map sum to the transformed character
            mapped_char = chr(ord('a') + (25 - total))
            result.append(mapped_char)
        
        return ''.join(result)
