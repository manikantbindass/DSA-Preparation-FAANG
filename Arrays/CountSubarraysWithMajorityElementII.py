"""
LeetCode Problem 3739: Count Subarrays With Majority Element II
Problem Number: 3739
Difficulty: Medium
Link: https://leetcode.com/problems/count-subarrays-with-majority-element-ii/

Given an array nums and an integer target, return the number of subarrays where the
majority element (appearing more than half the subarray length) is the target.

Example:
Input: nums = [1,2,1,1], target = 1
Output: 3

Constraints:
- 1 <= nums.length <= 10^5
- 0 <= nums[i] <= 10^9
- 0 <= target <= 10^9

Topics: Array, Binary Indexed Tree, Prefix Sum
Time Complexity: O(n log n) - for Fenwick Tree operations
Space Complexity: O(n) - for the BIT array
"""

class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.tree = [0] * (n + 1)
    
    def update(self, index: int, delta: int) -> None:
        while index <= self.n:
            self.tree[index] += delta
            index += index & -index
    
    def query(self, index: int) -> int:
        total = 0
        while index > 0:
            total += self.tree[index]
            index -= index & -index
        return total

class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        n = len(nums)
        bit = BinaryIndexedTree(2 * n + 1)
        
        # Offset to handle negative indices
        prefix = n + 1
        bit.update(prefix, 1)
        result = 0
        
        for num in nums:
            # Increment prefix if num is target, otherwise decrement
            prefix += 1 if num == target else -1
            # Count previous prefixes less than current prefix
            result += bit.query(prefix - 1)
            # Add current prefix to BIT
            bit.update(prefix, 1)
        
        return result
