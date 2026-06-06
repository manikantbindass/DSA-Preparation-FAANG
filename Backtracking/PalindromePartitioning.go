/*
LeetCode Problem 131: Palindrome Partitioning
Problem Number: 131
Difficulty: Medium
Link: https://leetcode.com/problems/palindrome-partitioning/

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
- 1 <= s.length <= 16
- s consists of lowercase English letters only.

Topics: String, Dynamic Programming, Backtracking
Time Complexity: O(n * 2^n) - for generating all partitions
Space Complexity: O(n^2) - for DP table and recursion stack
*/

package backtracking

func partition(s string) [][]string {
    n := len(s)
    // Precompute palindrome table using DP
    isPalindrome := make([][]bool, n)
    for i := 0; i < n; i++ {
        isPalindrome[i] = make([]bool, n)
        for j := 0; j < n; j++ {
            isPalindrome[i][j] = true
        }
    }
    
    for i := n - 1; i >= 0; i-- {
        for j := i + 1; j < n; j++ {
            isPalindrome[i][j] = (s[i] == s[j]) && isPalindrome[i+1][j-1]
        }
    }
    
    result := make([][]string, 0)
    current := make([]string, 0)
    
    var dfs func(start int)
    dfs = func(start int) {
        if start == n {
            temp := make([]string, len(current))
            copy(temp, current)
            result = append(result, temp)
            return
        }
        
        for end := start; end < n; end++ {
            if isPalindrome[start][end] {
                current = append(current, s[start:end+1])
                dfs(end + 1)
                current = current[:len(current)-1]
            }
        }
    }
    
    dfs(0)
    return result
}
