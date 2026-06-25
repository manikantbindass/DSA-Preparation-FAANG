"""
LeetCode Problem 3737: Count Subarrays With Majority Element I
Problem Number: 3737
Difficulty: Medium
Link: https://leetcode.com/problems/count-subarrays-with-majority-element-i/

Given an array of integers nums and an integer target, count the number of subarrays
where target appears more than half of the subarray length.

Example:
Input: nums = [1,2,1,3], target = 1
Output: 3
Explanation: The subarrays where 1 appears more than half are:
[1] (length 1, count 1), [1,2,1] (length 3, count 2), [1] (at index 2)

Constraints:
- 1 <= nums.length <= 10^5
- 1 <= nums[i] <= 10^9

Topics: Array, Hash Table, Sliding Window
Time Complexity: O(n^2) - brute force approach
Space Complexity: O(n) - for frequency map
"""

from typing import List
from collections import defaultdict

class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        n = len(nums)
        ans = 0
        
        # Check every subarray
        for i in range(n):
            freq = defaultdict(int)
            for j in range(i, n):
                length = j - i + 1
                freq[nums[j]] += 1
                if freq.get(target, 0) > length // 2:
                    ans += 1
        
        return ans
