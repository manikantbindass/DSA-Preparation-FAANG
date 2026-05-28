"""
LeetCode Problem 3093: Longest Common Suffix Queries
Problem Number: 3093
Difficulty: Hard
Link: https://leetcode.com/problems/longest-common-suffix-queries/

You are given two arrays of strings wordsContainer and wordsQuery.
For each query, find the word in wordsContainer that has the longest common suffix with the query word.
If there is a tie, return the word with the smallest index.

Example 1:
Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
Output: [1,1,0]

Example 2:
Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]
Output: [2,0,2]

Constraints:
- 1 <= wordsContainer.length, wordsQuery.length <= 10^4
- 1 <= wordsContainer[i].length, wordsQuery[i].length <= 5 * 10^3
- Sum of lengths of all strings in wordsContainer <= 5 * 10^5
- Sum of lengths of all strings in wordsQuery <= 5 * 10^5
- wordsContainer[i] and wordsQuery[i] consist only of lowercase English letters.

Topics: Array, String, Trie
Time Complexity: O(L1 + L2) - where L1 and L2 are total lengths of container and query words
Space Complexity: O(L1 * 26) - for the Trie nodes
"""

from typing import List

class TrieNode:
    __slots__ = ('children', 'min_len', 'min_idx')
    
    def __init__(self):
        self.children = [None] * 26
        self.min_len = float('inf')
        self.min_idx = float('inf')

class Solution:
    def stringIndices(self, wordsContainer: List[str], wordsQuery: List[str]) -> List[int]:
        root = TrieNode()
        
        # Insert all container words into the trie
        for idx, word in enumerate(wordsContainer):
            node = root
            # Update root with shortest word info
            if node.min_len > len(word):
                node.min_len = len(word)
                node.min_idx = idx
            
            # Insert from end to beginning (for suffix matching)
            for i in range(len(word) - 1, -1, -1):
                char_idx = ord(word[i]) - ord('a')
                if not node.children[char_idx]:
                    node.children[char_idx] = TrieNode()
                node = node.children[char_idx]
                
                # Update minimum length and index at this node
                if node.min_len > len(word):
                    node.min_len = len(word)
                    node.min_idx = idx
        
        result = []
        
        # Query each word
        for word in wordsQuery:
            node = root
            
            # Traverse the suffix of the query word
            for i in range(len(word) - 1, -1, -1):
                char_idx = ord(word[i]) - ord('a')
                if not node.children[char_idx]:
                    break
                node = node.children[char_idx]
            
            result.append(node.min_idx)
        
        return result
