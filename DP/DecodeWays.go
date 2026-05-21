/*
LeetCode Problem 91: Decode Ways
Problem Number: 91
Difficulty: Medium
Link: https://leetcode.com/problems/decode-ways/

A message containing letters from A-Z can be encoded into numbers using the mapping:
'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".

To decode an encoded message, all digits must be grouped and then mapped back into letters
using the reverse mapping. There may be multiple ways to decode a string.

Given a string s containing only digits, return the number of ways to decode it.

Example 1:
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero.

Constraints:
- 1 <= s.length <= 100
- s contains only digits and may contain leading zero(s).

Topics: String, Dynamic Programming
Time Complexity: O(n) - single pass through the string
Space Complexity: O(n) - for the DP array (can be optimized to O(1))
*/

package dp

import "strconv"

func numDecodings(s string) int {
    n := len(s)
    dp := make([]int, n+1)
    dp[0] = 1 // Base case: empty string has 1 way
    
    for i := 1; i <= n; i++ {
        // Single digit decode (1-9)
        if s[i-1] != '0' {
            dp[i] = dp[i-1]
        }
        
        // Two digit decode (10-26)
        if i > 1 && s[i-2] != '0' {
            twoDigits, _ := strconv.Atoi(s[i-2 : i])
            if twoDigits <= 26 {
                dp[i] += dp[i-2]
            }
        }
    }
    
    return dp[n]
}
