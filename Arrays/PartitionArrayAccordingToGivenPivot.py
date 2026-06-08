"""
LeetCode Problem 2161: Partition Array According to Given Pivot
Problem Number: 2161
Difficulty: Medium
Link: https://leetcode.com/problems/partition-array-according-to-given-pivot/

You are given a 0-indexed integer array nums and an integer pivot.
Rearrange nums such that the following conditions are satisfied:
- Every element less than pivot appears before every element greater than pivot.
- Every element equal to pivot appears in between the elements less than and greater than pivot.
- The relative order of the elements less than pivot and the elements greater than pivot is maintained.

Return the resulting array.

Example 1:
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation: Elements less than 10: 9,5,3 (maintain order), equal to 10: 10,10,
             greater than 10: 12,14 (maintain order).

Example 2:
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation: Elements less than 2: -3, equal to 2: 2, greater than 2: 4,3.

Constraints:
- 1 <= nums.length <= 10^5
- -10^6 <= nums[i] <= 10^6
- pivot is an element of nums (not necessarily unique).

Topics: Array, Two Pointers, Simulation
Time Complexity: O(n) - single pass through the array
Space Complexity: O(n) - for the result array
"""

from typing import List

class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        result = []
        
        # Add all elements less than pivot
        for num in nums:
            if num < pivot:
                result.append(num)
        
        # Add all elements equal to pivot
        for num in nums:
            if num == pivot:
                result.append(num)
        
        # Add all elements greater than pivot
        for num in nums:
            if num > pivot:
                result.append(num)
        
        return result
