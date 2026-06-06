"""
LeetCode Problem 2574: Left and Right Sum Differences
Problem Number: 2574
Difficulty: Easy
Link: https://leetcode.com/problems/left-and-right-sum-differences/

Given a 0-indexed integer array nums, find the array answer where answer[i] = |leftSum[i] - rightSum[i]|.
leftSum[i] is the sum of elements to the left of index i.
rightSum[i] is the sum of elements to the right of index i.

Example 1:
Input: nums = [10,4,8,3]
Output: [15,1,11,22]
Explanation: 
leftSum = [0,10,14,22], rightSum = [15,11,3,0]
answer = [|0-15|,|10-11|,|14-3|,|22-0|] = [15,1,11,22]

Example 2:
Input: nums = [1]
Output: [0]

Constraints:
- 1 <= nums.length <= 1000
- 1 <= nums[i] <= 10^5

Topics: Array, Prefix Sum
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - excluding the output array
"""

from typing import List

class Solution:
    def leftRightDifference(self, nums: List[int]) -> List[int]:
        n = len(nums)
        result = [0] * n
        
        # Compute total sum
        total_sum = sum(nums)
        left_sum = 0
        
        for i in range(n):
            # Remove current element from right sum
            total_sum -= nums[i]
            # Calculate absolute difference
            result[i] = abs(left_sum - total_sum)
            # Add current element to left sum for next iteration
            left_sum += nums[i]
        
        return result
