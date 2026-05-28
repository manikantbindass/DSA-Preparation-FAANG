/*
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
*/

package strings

type TrieNode struct {
    children [26]*TrieNode
    minLen   int
    minIdx   int
}

func stringIndices(wordsContainer []string, wordsQuery []string) []int {
    root := &TrieNode{minLen: int(^uint(0) >> 1), minIdx: int(^uint(0) >> 1)}
    
    // Insert all container words into the trie
    for idx, word := range wordsContainer {
        node := root
        // Update root with shortest word info
        if node.minLen > len(word) {
            node.minLen = len(word)
            node.minIdx = idx
        }
        
        // Insert from end to beginning (for suffix matching)
        for i := len(word) - 1; i >= 0; i-- {
            charIdx := word[i] - 'a'
            if node.children[charIdx] == nil {
                node.children[charIdx] = &TrieNode{minLen: int(^uint(0) >> 1), minIdx: int(^uint(0) >> 1)}
            }
            node = node.children[charIdx]
            
            // Update minimum length and index at this node
            if node.minLen > len(word) {
                node.minLen = len(word)
                node.minIdx = idx
            }
        }
    }
    
    result := make([]int, len(wordsQuery))
    
    // Query each word
    for i, word := range wordsQuery {
        node := root
        
        // Traverse the suffix of the query word
        for j := len(word) - 1; j >= 0; j-- {
            charIdx := word[j] - 'a'
            if node.children[charIdx] == nil {
                break
            }
            node = node.children[charIdx]
        }
        
        result[i] = node.minIdx
    }
    
    return result
}
