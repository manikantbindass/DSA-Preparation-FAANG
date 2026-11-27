# LeetCode Problem 3041: Maximize Consecutive Elements in an Array After Modification
# Difficulty: Hard
# Link: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/
# Approach: Process sorted values and update the incremented endpoint before the unchanged endpoint.
# Time Complexity: O(n log n + M), where M is the maximum value
# Space Complexity: O(n + M), including sorting


class Solution:
    def maxSelectedElements(self, nums: list[int]) -> int:
        ending = [0] * (max(nums) + 2)
        best = 0
        for value in sorted(nums):
            ending[value + 1] = max(ending[value + 1], ending[value] + 1)
            ending[value] = max(ending[value], ending[value - 1] + 1)
            best = max(best, ending[value], ending[value + 1])
        return best
