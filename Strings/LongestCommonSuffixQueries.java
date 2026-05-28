/*
 * LeetCode Problem 3093: Longest Common Suffix Queries
 * Problem Number: 3093
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/longest-common-suffix-queries/
 * 
 * You are given two arrays of strings wordsContainer and wordsQuery.
 * For each query, find the word in wordsContainer that has the longest common suffix with the query word.
 * If there is a tie, return the word with the smallest index.
 * 
 * Example 1:
 * Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
 * Output: [1,1,0]
 * 
 * Example 2:
 * Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]
 * Output: [2,0,2]
 * 
 * Constraints:
 * - 1 <= wordsContainer.length, wordsQuery.length <= 10^4
 * - 1 <= wordsContainer[i].length, wordsQuery[i].length <= 5 * 10^3
 * - Sum of lengths of all strings in wordsContainer <= 5 * 10^5
 * - Sum of lengths of all strings in wordsQuery <= 5 * 10^5
 * - wordsContainer[i] and wordsQuery[i] consist only of lowercase English letters.
 * 
 * Topics: Array, String, Trie
 * Time Complexity: O(L1 + L2) - where L1 and L2 are total lengths of container and query words
 * Space Complexity: O(L1 * 26) - for the Trie nodes
 */

class TrieNode {
    private static final int ALPHABET_SIZE = 26;
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];
    int minLength = Integer.MAX_VALUE;
    int minIndex = Integer.MAX_VALUE;
}

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word, int index) {
        TrieNode node = root;
        // Track minimum length and index at root
        if (root.minLength > word.length()) {
            root.minLength = word.length();
            root.minIndex = index;
        }
        
        // Insert from end to beginning (for suffix matching)
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
            
            // Update minimum length and index at this node
            if (node.minLength > word.length()) {
                node.minLength = word.length();
                node.minIndex = index;
            }
        }
    }
    
    public int search(String word) {
        TrieNode node = root;
        
        // Traverse the suffix of the query word
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        
        return node.minIndex;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        
        // Insert all container words into the trie
        for (int i = 0; i < wordsContainer.length; i++) {
            trie.insert(wordsContainer[i], i);
        }
        
        int[] result = new int[wordsQuery.length];
        
        // Query each word
        for (int i = 0; i < wordsQuery.length; i++) {
            result[i] = trie.search(wordsQuery[i]);
        }
        
        return result;
    }
}
