"""
LeetCode Problem 164: Maximum Gap
Problem Number: 164
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-gap/

Given an integer array nums, return the maximum difference between two successive elements
in its sorted form. If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form is [1,3,6,9], and the maximum gap is 3 (between 3 and 6, or 6 and 9).

Example 2:
Input: nums = [10]
Output: 0

Constraints:
- 1 <= nums.length <= 10^5
- 0 <= nums[i] <= 10^9

Topics: Array, Sorting, Bucket Sort
Time Complexity: O(n) - using bucket sort
Space Complexity: O(n) - for the buckets
"""

from typing import List

class Solution:
    def maximumGap(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return 0
        
        # Find the minimum and maximum values
        min_val = min(nums)
        max_val = max(nums)
        
        # If all elements are the same, gap is 0
        if min_val == max_val:
            return 0
        
        # Calculate bucket size and number of buckets
        bucket_size = max(1, (max_val - min_val) // (n - 1))
        bucket_count = (max_val - min_val) // bucket_size + 1
        
        # Initialize buckets with min and max values
        buckets = [[float('inf'), float('-inf')] for _ in range(bucket_count)]
        
        # Place each number into its bucket
        for num in nums:
            index = (num - min_val) // bucket_size
            buckets[index][0] = min(buckets[index][0], num)
            buckets[index][1] = max(buckets[index][1], num)
        
        # Calculate maximum gap
        max_gap = 0
        prev_max = min_val
        for bucket in buckets:
            # Skip empty buckets
            if bucket[0] == float('inf'):
                continue
            # Gap between previous bucket's max and current bucket's min
            max_gap = max(max_gap, bucket[0] - prev_max)
            prev_max = bucket[1]
        
        return max_gap
