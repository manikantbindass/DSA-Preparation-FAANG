/*
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
*/

package graphs

func findLadders(beginWord string, endWord string, wordList []string) [][]string {
    result := make([][]string, 0)
    words := make(map[string]bool)
    for _, w := range wordList {
        words[w] = true
    }
    
    if !words[endWord] {
        return result
    }
    
    delete(words, beginWord)
    
    // BFS to find shortest paths and record predecessors
    distance := make(map[string]int)
    distance[beginWord] = 0
    predecessors := make(map[string]map[string]bool)
    queue := []string{beginWord}
    found := false
    
    for len(queue) > 0 && !found {
        levelSize := len(queue)
        for i := 0; i < levelSize; i++ {
            current := queue[0]
            queue = queue[1:]
            currentDist := distance[current]
            chars := []byte(current)
            
            // Try changing each character
            for j := 0; j < len(chars); j++ {
                original := chars[j]
                for c := byte('a'); c <= 'z'; c++ {
                    if c == original {
                        continue
                    }
                    chars[j] = c
                    nextWord := string(chars)
                    
                    if words[nextWord] {
                        // Record predecessor relationship
                        if predecessors[nextWord] == nil {
                            predecessors[nextWord] = make(map[string]bool)
                        }
                        predecessors[nextWord][current] = true
                        
                        // If we found endWord, mark found
                        if nextWord == endWord {
                            found = true
                        }
                        
                        // If this word hasn't been visited, add to queue
                        if _, exists := distance[nextWord]; !exists {
                            distance[nextWord] = currentDist + 1
                            if !found {
                                queue = append(queue, nextWord)
                            }
                        }
                    }
                }
                chars[j] = original
            }
        }
    }
    
    // If endWord was found, reconstruct all shortest paths using DFS
    if found {
        path := []string{endWord}
        var dfs func(current string)
        dfs = func(current string) {
            if current == beginWord {
                // Create a copy of the reversed path
                temp := make([]string, len(path))
                for i := 0; i < len(path); i++ {
                    temp[i] = path[len(path)-1-i]
                }
                result = append(result, temp)
                return
            }
            
            for predecessor := range predecessors[current] {
                path = append(path, predecessor)
                dfs(predecessor)
                path = path[:len(path)-1]
            }
        }
        dfs(endWord)
    }
    
    return result
}
