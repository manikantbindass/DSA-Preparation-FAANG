/*
LeetCode Problem 139: Word Break
Problem Number: 139
Difficulty: Medium
Link: https://leetcode.com/problems/word-break/

Given a string s and a dictionary of strings wordDict, return true if s can be segmented
into a space-separated sequence of one or more dictionary words.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:
- 1 <= s.length <= 300
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- s and wordDict[i] consist of lowercase English letters.

Topics: Array, Hash Table, String, Dynamic Programming, Trie, Memoization
Time Complexity: O(n^2) - where n is the length of s
Space Complexity: O(n) - for the DP array
*/

package dp

func wordBreak(s string, wordDict []string) bool {
    wordSet := make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }
    
    n := len(s)
    dp := make([]bool, n+1)
    dp[0] = true // Empty string is always segmentable
    
    for i := 1; i <= n; i++ {
        for j := 0; j < i; j++ {
            if dp[j] && wordSet[s[j:i]] {
                dp[i] = true
                break
            }
        }
    }
    
    return dp[n]
}
