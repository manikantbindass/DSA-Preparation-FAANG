"""
LeetCode Problem 152: Maximum Product Subarray
Problem Number: 152
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-product-subarray/

Given an integer array nums, find a contiguous non-empty subarray within the array
that has the largest product, and return the product.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:
- 1 <= nums.length <= 2 * 10^4
- -10 <= nums[i] <= 10

Topics: Array, Dynamic Programming
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - only using constant extra space
"""

from typing import List

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        max_product = nums[0]
        current_max = nums[0]
        current_min = nums[0]
        
        for i in range(1, len(nums)):
            num = nums[i]
            
            # If num is negative, max and min swap
            if num < 0:
                current_max, current_min = current_min, current_max
            
            # Update current max and min
            current_max = max(num, current_max * num)
            current_min = min(num, current_min * num)
            
            # Update global maximum
            max_product = max(max_product, current_max)
        
        return max_product
