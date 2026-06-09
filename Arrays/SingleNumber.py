"""
LeetCode Problem 136: Single Number
Problem Number: 136
Difficulty: Easy
Link: https://leetcode.com/problems/single-number/

Given a non-empty array of integers nums, every element appears twice except for one.
Find that single one.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1

Constraints:
- 1 <= nums.length <= 3 * 10^4
- -3 * 10^4 <= nums[i] <= 3 * 10^4
- Each element appears twice except for one element which appears once.

Topics: Array, Bit Manipulation
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - only using constant extra space
"""

from typing import List

class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        result = 0
        for num in nums:
            result ^= num
        return result
