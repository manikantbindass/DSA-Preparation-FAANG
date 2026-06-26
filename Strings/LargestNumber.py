"""
LeetCode Problem 179: Largest Number
Problem Number: 179
Difficulty: Medium
Link: https://leetcode.com/problems/largest-number/

Given a list of non-negative integers nums, arrange them such that they form the largest number.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:
- 1 <= nums.length <= 100
- 0 <= nums[i] <= 10^9

Topics: String, Sorting, Greedy
Time Complexity: O(n log n) - for sorting
Space Complexity: O(n) - for the string array
"""

from typing import List
from functools import cmp_to_key

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        # Convert numbers to strings
        num_strs = [str(num) for num in nums]
        
        # Sort using custom comparator: compare concatenated results
        def compare(a: str, b: str) -> int:
            if a + b > b + a:
                return -1
            elif a + b < b + a:
                return 1
            else:
                return 0
        
        num_strs.sort(key=cmp_to_key(compare))
        
        # If the largest number is "0", the result should be "0"
        if num_strs[0] == "0":
            return "0"
        
        # Build the result
        return ''.join(num_strs)
