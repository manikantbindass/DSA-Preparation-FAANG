"""
LeetCode Problem 2784: Check if Array is Good
Problem Number: 2784
Difficulty: Easy
Link: https://leetcode.com/problems/check-if-array-is-good/

You are given an integer array nums. The array is called good if it is a permutation 
of an array base[n] where base = [1, 2, ..., n-1, n, n] (n appears twice, all other 
numbers appear exactly once).

Return true if the array is good, otherwise false.

Example 1:
Input: nums = [2, 1, 3]
Output: false
Explanation: Since n = 3, base = [1, 2, 3, 3]. 
The array nums does not contain both 3's.

Example 2:
Input: nums = [1, 3, 3, 2]
Output: true
Explanation: n = 3, base = [1, 2, 3, 3]. 
The array nums has the same elements as base.

Example 3:
Input: nums = [1, 1]
Output: true
Explanation: n = 1, base = [1, 1]. The array nums matches base.

Constraints:
- 1 <= nums.length <= 100
- 1 <= nums[i] <= 200

Topics: Array, Hash Table, Sorting
Time Complexity: O(n) - single pass through the array
Space Complexity: O(limit) - frequency array of size 201
"""

from collections import Counter

class Solution:
    def isGood(self, nums: list[int]) -> bool:
        n = len(nums) - 1
        
        # Count frequencies of all numbers
        freq = Counter(nums)
        
        # The maximum element n should appear exactly twice
        if freq.get(n, 0) != 2:
            return False
        
        # All elements from 1 to n-1 should appear exactly once
        for i in range(1, n):
            if freq.get(i, 0) != 1:
                return False
        
        return True
