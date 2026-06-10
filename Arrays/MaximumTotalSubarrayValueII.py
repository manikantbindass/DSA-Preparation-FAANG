"""
LeetCode Problem 3691: Maximum Total Subarray Value II
Problem Number: 3691
Difficulty: Hard
Link: https://leetcode.com/problems/maximum-total-subarray-value-ii/

Given an array nums and an integer k, find the maximum total value by selecting k subarrays
where the value of a subarray is defined as its max - min.

Example:
Input: nums = [1,2,3,4,5], k = 2
Output: 8

Constraints:
- 1 <= nums.length <= 10^5
- 1 <= nums[i] <= 10^9
- 1 <= k <= 10^5

Topics: Array, Sparse Table, Priority Queue, Greedy
Time Complexity: O(n log n + k log n) - building sparse table and processing k operations
Space Complexity: O(n log n) - for the sparse table
"""

import heapq
import math

class SparseTable:
    def __init__(self, arr):
        n = len(arr)
        # Precompute powers of 2
        self.pow = [0] * (n + 1)
        for i in range(2, n + 1):
            self.pow[i] = self.pow[i >> 1] + 1
        
        max_log = self.pow[n]
        self.max_table = [[0] * n for _ in range(max_log + 1)]
        self.min_table = [[0] * n for _ in range(max_log + 1)]
        
        # Initialize level 0
        self.max_table[0] = arr[:]
        self.min_table[0] = arr[:]
        
        # Build sparse table
        for p in range(1, max_log + 1):
            length = n - (1 << p)
            prev_len = 1 << (p - 1)
            for i in range(length + 1):
                self.max_table[p][i] = max(self.max_table[p - 1][i + prev_len], self.max_table[p - 1][i])
                self.min_table[p][i] = min(self.min_table[p - 1][i + prev_len], self.min_table[p - 1][i])
    
    def query(self, left, right):
        p = self.pow[right - left + 1]
        max_val = max(self.max_table[p][right - (1 << p) + 1], self.max_table[p][left])
        min_val = min(self.min_table[p][right - (1 << p) + 1], self.min_table[p][left])
        return max_val - min_val

class Solution:
    def maxTotalValue(self, nums, k):
        n = len(nums)
        st = SparseTable(nums)
        # Max heap stores (-value, left, right) for Python's min-heap
        pq = [(-st.query(0, n - 1), 0, n - 1)]
        total = 0
        
        for _ in range(k):
            neg_val, left, right = heapq.heappop(pq)
            val = -neg_val
            total += val
            
            if left < right:
                # Left part: from left to right-1
                heapq.heappush(pq, (-st.query(left, right - 1), left, right - 1))
                # Right part: from left+1 to right
                heapq.heappush(pq, (-st.query(left + 1, right), left + 1, right))
        
        return total
