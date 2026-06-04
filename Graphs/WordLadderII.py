"""
LeetCode Problem 126: Word Ladder II
Problem Number: 126
Difficulty: Hard
Link: https://leetcode.com/problems/word-ladder-ii/

A transformation sequence from word beginWord to word endWord using a dictionary wordList
is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
- Every adjacent pair of words differs by a single letter.
- Every si for 1 <= i <= k is in wordList.
- beginWord may not be in wordList, but endWord must be.

Return all shortest transformation sequences from beginWord to endWord.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []

Constraints:
- 1 <= beginWord.length <= 5
- endWord.length == beginWord.length
- 1 <= wordList.length <= 500
- wordList[i].length == beginWord.length
- beginWord, endWord, and wordList[i] consist of lowercase English letters.

Topics: Hash Table, String, Backtracking, Breadth-First Search, Graph
Time Complexity: O(N * L^2) - where N is number of words, L is word length
Space Complexity: O(N * L) - for storing graph and paths
"""

from collections import defaultdict, deque
from typing import List

class Solution:
    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        words = set(wordList)
        if endWord not in words:
            return []
        
        words.discard(beginWord)
        
        # BFS to find shortest paths and record predecessors
        distance = {beginWord: 0}
        predecessors = defaultdict(set)
        queue = deque([beginWord])
        found = False
        
        while queue and not found:
            level_size = len(queue)
            for _ in range(level_size):
                current = queue.popleft()
                current_dist = distance[current]
                chars = list(current)
                
                # Try changing each character
                for i in range(len(chars)):
                    original = chars[i]
                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        if c == original:
                            continue
                        chars[i] = c
                        next_word = ''.join(chars)
                        
                        if next_word in words:
                            # Record predecessor relationship
                            predecessors[next_word].add(current)
                            
                            # If we found endWord, mark found
                            if next_word == endWord:
                                found = True
                            
                            # If this word hasn't been visited, add to queue
                            if next_word not in distance:
                                distance[next_word] = current_dist + 1
                                if not found:
                                    queue.append(next_word)
                    chars[i] = original
        
        # If endWord was found, reconstruct all shortest paths using DFS
        result = []
        
        def dfs(current: str, path: List[str]):
            if current == beginWord:
                result.append(path[::-1])
                return
            
            for predecessor in predecessors.get(current, []):
                path.append(predecessor)
                dfs(predecessor, path)
                path.pop()
        
        if found:
            dfs(endWord, [endWord])
        
        return result
