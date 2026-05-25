"""
LeetCode Problem 1871: Jump Game VII
Problem Number: 1871
Difficulty: Medium
Link: https://leetcode.com/problems/jump-game-vii/

You are given a 0-indexed binary string s and two integers minJump and maxJump.
In the beginning, you are at index 0, which is guaranteed to be '0'.
You can jump from index i to index j if:
- i + minJump <= j <= min(i + maxJump, s.length - 1)
- s[j] == '0'

Return true if you can reach index s.length - 1, otherwise return false.

Example 1:
Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation: 0 -> 3 -> 5 (0-indexed positions)

Example 2:
Input: s = "011011", minJump = 2, maxJump = 3
Output: false
Explanation: Cannot reach last index because s[5] is '1'

Constraints:
- 2 <= s.length <= 10^5
- 1 <= minJump <= maxJump < s.length
- s[0] == '0'

Topics: String, Dynamic Programming, Prefix Sum, Sliding Window
Time Complexity: O(n) - using prefix sum for range queries
Space Complexity: O(n) - for DP and prefix arrays
"""

class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        dp = [False] * n
        prefix = [0] * (n + 1)
        
        # Starting position is always reachable
        dp[0] = True
        prefix[1] = 1
        
        for i in range(1, n):
            # Only consider positions where s[i] == '0'
            if s[i] == '0':
                left = max(0, i - maxJump)
                right = i - minJump
                
                # Check if there's any reachable position in the range [left, right]
                if left <= right and prefix[right + 1] - prefix[left] > 0:
                    dp[i] = True
            
            # Update prefix sum
            prefix[i + 1] = prefix[i] + (1 if dp[i] else 0)
        
        return dp[n - 1]
