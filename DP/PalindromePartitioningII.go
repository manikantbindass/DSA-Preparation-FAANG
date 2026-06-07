/*
LeetCode Problem 132: Palindrome Partitioning II
Problem Number: 132
Difficulty: Hard
Link: https://leetcode.com/problems/palindrome-partitioning-ii/

Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

Example 2:
Input: s = "a"
Output: 0

Example 3:
Input: s = "ab"
Output: 1

Constraints:
- 1 <= s.length <= 2000
- s consists of lowercase English letters only.

Topics: String, Dynamic Programming
Time Complexity: O(n^2) - where n is the length of the string
Space Complexity: O(n^2) - for the palindrome DP table
*/

package dp

func minCut(s string) int {
    n := len(s)
    // dp[i][j] = true if substring s[i..j] is palindrome
    isPalindrome := make([][]bool, n)
    for i := 0; i < n; i++ {
        isPalindrome[i] = make([]bool, n)
        for j := 0; j < n; j++ {
            isPalindrome[i][j] = true
        }
    }
    
    // Build palindrome table using DP
    for i := n - 1; i >= 0; i-- {
        for j := i + 1; j < n; j++ {
            isPalindrome[i][j] = (s[i] == s[j]) && isPalindrome[i+1][j-1]
        }
    }
    
    // cuts[i] = minimum cuts needed for substring s[0..i]
    cuts := make([]int, n)
    for i := 0; i < n; i++ {
        cuts[i] = i // Maximum cuts needed (cut after each character)
    }
    
    for i := 1; i < n; i++ {
        for j := 0; j <= i; j++ {
            if isPalindrome[j][i] {
                if j == 0 {
                    cuts[i] = 0 // Whole substring is palindrome
                } else {
                    if 1+cuts[j-1] < cuts[i] {
                        cuts[i] = 1 + cuts[j-1]
                    }
                }
            }
        }
    }
    
    return cuts[n-1]
}
