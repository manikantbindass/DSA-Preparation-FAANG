/*
 * LeetCode Problem 126: Word Ladder II
 * Problem Number: 126
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/word-ladder-ii/
 * 
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * - Every adjacent pair of words differs by a single letter.
 * - Every si for 1 <= i <= k is in wordList.
 * - beginWord may not be in wordList, but endWord must be.
 * 
 * Return all shortest transformation sequences from beginWord to endWord.
 * 
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * 
 * Constraints:
 * - 1 <= beginWord.length <= 5
 * - endWord.length == beginWord.length
 * - 1 <= wordList.length <= 500
 * - wordList[i].length == beginWord.length
 * - beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * 
 * Topics: Hash Table, String, Backtracking, Breadth-First Search, Graph
 * Time Complexity: O(N * L^2) - where N is number of words, L is word length
 * Space Complexity: O(N * L) - for storing graph and paths
 */

import java.util.*;

class Solution {
    private List<List<String>> result = new ArrayList<>();
    private Map<String, Set<String>> predecessors = new HashMap<>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return result;
        }
        
        // Remove beginWord if it exists in the set
        words.remove(beginWord);
        
        // BFS to find shortest paths and record predecessors
        Map<String, Integer> distance = new HashMap<>();
        distance.put(beginWord, 0);
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        
        boolean found = false;
        
        while (!queue.isEmpty() && !found) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                int currentDist = distance.get(current);
                char[] chars = current.toCharArray();
                
                // Try changing each character
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String next = new String(chars);
                        
                        if (words.contains(next)) {
                            // If we found endWord, mark found and don't add to queue further
                            if (next.equals(endWord)) {
                                found = true;
                            }
                            
                            // Record predecessor relationship
                            predecessors.computeIfAbsent(next, k -> new HashSet<>()).add(current);
                            
                            // If this word hasn't been visited, add to queue
                            if (!distance.containsKey(next)) {
                                distance.put(next, currentDist + 1);
                                if (!found) {
                                    queue.offer(next);
                                }
                            }
                        }
                    }
                    chars[j] = original;
                }
            }
        }
        
        // If endWord was found, reconstruct all shortest paths using DFS
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(endWord, beginWord, path);
        }
        
        return result;
    }
    
    private void dfs(String current, String beginWord, Deque<String> path) {
        if (current.equals(beginWord)) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        Set<String> preds = predecessors.get(current);
        if (preds != null) {
            for (String predecessor : preds) {
                path.addFirst(predecessor);
                dfs(predecessor, beginWord, path);
                path.removeFirst();
            }
        }
    }
}
