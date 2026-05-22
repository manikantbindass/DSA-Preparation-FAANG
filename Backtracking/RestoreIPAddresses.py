"""
LeetCode Problem 93: Restore IP Addresses
Problem Number: 93
Difficulty: Medium
Link: https://leetcode.com/problems/restore-ip-addresses/

A valid IP address consists of exactly four integers separated by single dots.
Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

Given a string s containing only digits, return all possible valid IP addresses
that can be formed by inserting dots into s. You may return them in any order.

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:
- 1 <= s.length <= 20
- s consists of digits only.

Topics: String, Backtracking
Time Complexity: O(3^4) - constant (at most 3 choices per segment, 4 segments)
Space Complexity: O(1) - excluding output space
"""

from typing import List

class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        n = len(s)
        ans = []
        segments = []
        
        def dfs(start: int):
            # If we have 4 segments and used all characters, it's a valid IP
            if len(segments) == 4 and start == n:
                ans.append(".".join(segments))
                return
            
            # If we have 4 segments but still have characters left, or vice versa
            if len(segments) >= 4 or start >= n:
                return
            
            num = 0
            # Try segment lengths of 1, 2, or 3 digits
            for end in range(start, min(start + 3, n)):
                num = num * 10 + int(s[end])
                
                # Check for invalid number (>255) or leading zero
                if num > 255 or (s[start] == '0' and start != end):
                    break
                
                segments.append(s[start:end + 1])
                dfs(end + 1)
                segments.pop()
        
        dfs(0)
        return ans
