/*
 * LeetCode Problem 140: Word Break II
 * Problem Number: 140
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/word-break-ii/
 * 
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * 
 * Example 1:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * 
 * Example 2:
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 * 
 * Constraints:
 * - 1 <= s.length <= 20
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 20
 * - s and wordDict[i] consist of lowercase English letters.
 * 
 * Topics: Array, Hash Table, String, Dynamic Programming, Backtracking, Trie, Memoization
 * Time Complexity: O(2^n) - worst case for generating all partitions
 * Space Complexity: O(n) - for recursion stack and memoization
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    
    void insert(String word) {
        TrieNode node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }
    
    boolean search(String word) {
        TrieNode node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return false;
            }
            node = node.children[idx];
        }
        return node.isEnd;
    }
}

class Solution {
    private TrieNode trie = new TrieNode();
    private Map<String, List<String>> memo = new HashMap<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Build Trie from wordDict
        for (String word : wordDict) {
            trie.insert(word);
        }
        
        return dfs(s);
    }
    
    private List<String> dfs(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        List<String> result = new ArrayList<>();
        
        // Base case: empty string
        if (s.isEmpty()) {
            result.add("");
            memo.put(s, result);
            return result;
        }
        
        // Try all prefixes of s
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (trie.search(prefix)) {
                // Recursively get sentences for the remaining substring
                List<String> suffixSentences = dfs(s.substring(i));
                for (String suffix : suffixSentences) {
                    if (suffix.isEmpty()) {
                        result.add(prefix);
                    } else {
                        result.add(prefix + " " + suffix);
                    }
                }
            }
        }
        
        memo.put(s, result);
        return result;
    }
}
