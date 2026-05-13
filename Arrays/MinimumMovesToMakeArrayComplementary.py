"""
LeetCode Problem 1674: Minimum Moves to Make Array Complementary
Problem Number: 1674
Difficulty: Medium
Link: https://leetcode.com/problems/minimum-moves-to-make-array-complementary/

You are given an integer array nums of even length n and an integer limit.
In one move, you can replace any element with any integer between 1 and limit inclusive.
The array is complementary if for all i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number.

Return the minimum number of moves required to make the array complementary.

Example 1:
Input: nums = [1,2,4,3], limit = 4
Output: 1
Explanation: Replace 1 with 2, then sums become 2+3=5, 4+1=5.

Example 2:
Input: nums = [1,2,2,1], limit = 2
Output: 2

Example 3:
Input: nums = [1,2,1,2], limit = 2
Output: 0

Constraints:
- n == nums.length
- 2 <= n <= 10^5
- n is even
- 1 <= nums[i] <= limit <= 10^5

Topics: Array, Greedy, Prefix Sum, Difference Array
Time Complexity: O(n + limit) - single pass through pairs and difference array
Space Complexity: O(limit) - difference array of size 2*limit+2
"""

class Solution:
    def minMoves(self, nums: list[int], limit: int) -> int:
        diff = [0] * (2 * limit + 2)
        n = len(nums)
        
        for i in range(n // 2):
            a = nums[i]
            b = nums[n - i - 1]
            low = min(a, b)
            high = max(a, b)
            total = a + b
            
            # Start with all sums requiring 2 moves
            diff[2] += 2
            
            # Range adjustments for different move counts
            diff[low + 1] -= 1
            diff[total] -= 1
            diff[total + 1] += 1
            diff[high + limit + 1] += 1
        
        ans = n
        current = 0
        for i in range(2, len(diff)):
            current += diff[i]
            ans = min(ans, current)
        
        return ans
