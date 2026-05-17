"""
LeetCode Problem 1306: Jump Game III
Problem Number: 1306
Difficulty: Medium
Link: https://leetcode.com/problems/jump-game-iii/

Given an array of non-negative integers arr, you are initially positioned at start index.
When you are at index i, you can jump to i + arr[i] or i - arr[i], 
provided the destination index is within bounds.

Return true if you can reach any index with value 0, otherwise return false.

Example 1:
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
Index 5 -> index 4 (5 - 1) -> index 1 (4 - 3) -> index 3 (1 + 2) which has value 0.

Example 2:
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation: Index 0 -> index 4 (0 + 4) -> index 1 (4 - 3) -> index 3 (1 + 2) has value 0.

Example 3:
Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: Cannot reach index with value 0.

Constraints:
- 1 <= arr.length <= 5 * 10^4
- 0 <= arr[i] < arr.length
- 0 <= start < arr.length

Topics: Array, Depth-First Search, Breadth-First Search
Time Complexity: O(n) - each index is visited at most once
Space Complexity: O(n) - for the queue and visited array
"""

from collections import deque
from typing import List

class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        queue = deque([start])
        n = len(arr)
        
        while queue:
            i = queue.popleft()
            
            # Found a zero value
            if arr[i] == 0:
                return True
            
            jump = arr[i]
            # Mark as visited by setting to -1
            arr[i] = -1
            
            # Explore both directions
            for next_idx in (i + jump, i - jump):
                if 0 <= next_idx < n and arr[next_idx] >= 0:
                    queue.append(next_idx)
        
        return False
