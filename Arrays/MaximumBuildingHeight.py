"""
LeetCode Problem 1840: Maximum Building Height
Problem Number: 1840
Difficulty: Hard
Link: https://leetcode.com/problems/maximum-building-height/

You are given an integer n and a 2D integer array restrictions where restrictions[i] = [id_i, maxHeight_i]
indicates that building id_i has a height restriction of maxHeight_i.

There are n buildings numbered 1 to n. The height of each building must be a non-negative integer.
The height difference between adjacent buildings cannot exceed 1.

Return the maximum possible height of the tallest building.

Example 1:
Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The heights could be [0,1,1,1,1] or [1,1,1,1,1]? Actually, with restrictions, max height is 2.

Example 2:
Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5

Example 3:
Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2

Constraints:
- 2 <= n <= 10^9
- 0 <= restrictions.length <= min(n - 1, 10^5)
- 1 <= id_i <= n
- 0 <= maxHeight_i <= 10^9
- id_i is unique.

Topics: Array, Greedy, Sorting
Time Complexity: O(m log m) - where m is the number of restrictions
Space Complexity: O(m) - for storing restrictions list
"""

from typing import List

class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        # Add implicit restrictions and sort by building ID
        restrictions = [r[:] for r in restrictions]  # Copy to avoid modification
        restrictions.append([1, 0])  # Building 1 must be at least height 0
        restrictions.sort(key=lambda x: x[0])
        
        # Add implicit restriction for the last building (n)
        if restrictions[-1][0] != n:
            restrictions.append([n, n - 1])
        
        m = len(restrictions)
        
        # First pass: left to right - enforce restrictions based on previous building
        for i in range(1, m):
            prev = restrictions[i - 1]
            curr = restrictions[i]
            dist = curr[0] - prev[0]
            curr[1] = min(curr[1], prev[1] + dist)
        
        # Second pass: right to left - enforce restrictions based on next building
        for i in range(m - 2, 0, -1):
            curr = restrictions[i]
            next_rest = restrictions[i + 1]
            dist = next_rest[0] - curr[0]
            curr[1] = min(curr[1], next_rest[1] + dist)
        
        # Calculate maximum possible height between each pair of restricted buildings
        max_height = 0
        for i in range(m - 1):
            left = restrictions[i]
            right = restrictions[i + 1]
            dist = right[0] - left[0]
            # Maximum height achievable between two restrictions
            possible_height = (left[1] + right[1] + dist) // 2
            max_height = max(max_height, possible_height)
        
        return max_height
