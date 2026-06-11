"""
LeetCode Problem 140: Word Break II
Problem Number: 140
Difficulty: Hard
Link: https://leetcode.com/problems/word-break-ii/

Given a string s and a dictionary of strings wordDict, add spaces in s to construct
a sentence where each word is a valid dictionary word. Return all such possible sentences.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
- 1 <= s.length <= 20
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- s and wordDict[i] consist of lowercase English letters.

Topics: Array, Hash Table, String, Dynamic Programming, Backtracking, Trie, Memoization
Time Complexity: O(2^n) - worst case for generating all partitions
Space Complexity: O(n) - for recursion stack and memoization
"""

from typing import List
from functools import lru_cache

class TrieNode:
    __slots__ = ('children', 'is_end')
    
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        # Build Trie from wordDict
        root = TrieNode()
        for word in wordDict:
            node = root
            for ch in word:
                idx = ord(ch) - ord('a')
                if not node.children[idx]:
                    node.children[idx] = TrieNode()
                node = node.children[idx]
            node.is_end = True
        
        @lru_cache(maxsize=None)
        def dfs(string: str) -> List[str]:
            if not string:
                return [""]
            
            result = []
            node = root
            
            for i, ch in enumerate(string):
                idx = ord(ch) - ord('a')
                if not node.children[idx]:
                    break
                node = node.children[idx]
                
                if node.is_end:
                    prefix = string[:i + 1]
                    suffix_sentences = dfs(string[i + 1:])
                    for suffix in suffix_sentences:
                        if suffix:
                            result.append(prefix + " " + suffix)
                        else:
                            result.append(prefix)
            
            return result
        
        return dfs(s)
