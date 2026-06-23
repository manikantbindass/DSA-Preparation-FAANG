"""
LeetCode Problem 3699: Number of ZigZag Arrays I
Problem Number: 3699
Difficulty: Hard
Link: https://leetcode.com/problems/number-of-zigzag-arrays-i/

A zigzag array is an array where every adjacent pair of elements alternates
between greater and less than the previous element.

Given n (length of array) and a range [l, r] of possible values,
count the number of zigzag arrays of length n where each element is in [l, r].

Example:
Input: n = 3, l = 1, r = 3
Output: 10

Constraints:
- 1 <= n <= 10^5
- 1 <= l <= r <= 10^5

Topics: Dynamic Programming, Math, Combinatorics
Time Complexity: O(n * m) - where m = r - l + 1
Space Complexity: O(m) - for DP arrays
"""

MOD = 10**9 + 7

class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        m = r - l + 1
        
        # dp_up[i] = number of valid sequences ending with value i
        # where the last step was an upward move (current > previous)
        # dp_down[i] = number of valid sequences ending with value i
        # where the last step was a downward move (current < previous)
        dp_up = [1] * m
        dp_down = [1] * m
        
        # Build sequences of length 2 to n
        for _ in range(2, n + 1):
            # Prefix sums for dp_down
            prefix_down = [0] * (m + 1)
            for i in range(m):
                prefix_down[i + 1] = (prefix_down[i] + dp_down[i]) % MOD
            
            # Suffix sums for dp_up
            suffix_up = [0] * (m + 1)
            for i in range(m - 1, -1, -1):
                suffix_up[i] = (suffix_up[i + 1] + dp_up[i]) % MOD
            
            new_up = [0] * m
            new_down = [0] * m
            
            for x in range(m):
                # For downward move: previous value must be greater than current
                new_down[x] = suffix_up[x + 1]
                
                # For upward move: previous value must be less than current
                new_up[x] = prefix_down[x]
            
            dp_up = new_up
            dp_down = new_down
        
        # Sum all sequences of length n
        total = 0
        for i in range(m):
            total = (total + dp_up[i] + dp_down[i]) % MOD
        
        return total
