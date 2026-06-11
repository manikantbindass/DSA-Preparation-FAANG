/*
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
*/

package backtracking

type TrieNode struct {
    children [26]*TrieNode
    isEnd    bool
}

func (t *TrieNode) insert(word string) {
    node := t
    for i := 0; i < len(word); i++ {
        idx := word[i] - 'a'
        if node.children[idx] == nil {
            node.children[idx] = &TrieNode{}
        }
        node = node.children[idx]
    }
    node.isEnd = true
}

func wordBreak(s string, wordDict []string) []string {
    root := &TrieNode{}
    for _, word := range wordDict {
        root.insert(word)
    }
    
    memo := make(map[string][]string)
    
    var dfs func(string) []string
    dfs = func(str string) []string {
        if val, exists := memo[str]; exists {
            return val
        }
        
        if len(str) == 0 {
            return []string{""}
        }
        
        result := make([]string, 0)
        node := root
        
        for i := 0; i < len(str); i++ {
            idx := str[i] - 'a'
            if node.children[idx] == nil {
                break
            }
            node = node.children[idx]
            
            if node.isEnd {
                prefix := str[:i+1]
                suffixSentences := dfs(str[i+1:])
                for _, suffix := range suffixSentences {
                    if suffix == "" {
                        result = append(result, prefix)
                    } else {
                        result = append(result, prefix+" "+suffix)
                    }
                }
            }
        }
        
        memo[str] = result
        return result
    }
    
    return dfs(s)
}
