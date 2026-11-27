# LeetCode Problem 2567: Minimum Score by Changing Two Elements
# Difficulty: Medium
# Link: https://leetcode.com/problems/minimum-score-by-changing-two-elements/
# Approach: Sort and compare the three ranges left after removing two extreme elements.
# Time Complexity: O(n log n)
# Space Complexity: O(n) including sorting, O(1) apart from sorting


class Solution:
    def minimizeSum(self, nums: list[int]) -> int:
        ordered = sorted(nums)
        return min(ordered[-3] - ordered[0], ordered[-2] - ordered[1], ordered[-1] - ordered[2])
