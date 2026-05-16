"""
LeetCode Problem 154: Find Minimum in Rotated Sorted Array II
Problem Number: 154
Difficulty: Hard
Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,4,4,5,6,7] might become:
- [4,5,6,7,0,1,4] if rotated 4 times.
- [0,1,4,4,5,6,7] if rotated 0 times.

Given the sorted rotated array nums that may contain duplicates, return the minimum element.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [1,3,5]
Output: 1

Example 2:
Input: nums = [2,2,2,0,1]
Output: 0

Constraints:
- n == nums.length
- 1 <= n <= 5000
- -5000 <= nums[i] <= 5000
- nums is sorted and rotated between 1 and n times.

Topics: Array, Binary Search
Time Complexity: O(log n) on average, O(n) worst case when many duplicates
Space Complexity: O(1) - only using constant extra space
"""

from typing import List

class Solution:
    def findMin(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        
        while left < right:
            mid = (left + right) // 2
            
            # If mid element is greater than right element, minimum is in right half
            if nums[mid] > nums[right]:
                left = mid + 1
            # If mid element is less than right element, minimum is in left half
            elif nums[mid] < nums[right]:
                right = mid
            # When equal, we cannot determine which side contains the minimum
            # So we safely decrement right pointer
            else:
                right -= 1
        
        return nums[left]
