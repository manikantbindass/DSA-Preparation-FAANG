# LeetCode Problem 4120: Count Dominant Elements
# Difficulty: Easy
# Link: https://leetcode.com/problems/count-dominant-elements/
# Approach: Compare each value times its neighbor count with the sum of its neighbors.
# Time Complexity: O(n)
# Space Complexity: O(1)


class Solution:
    def countDominantElements(self, nums: list[int]) -> int:
        answer = 0
        for i, value in enumerate(nums):
            neighbor_count = neighbor_sum = 0
            if i > 0:
                neighbor_count += 1
                neighbor_sum += nums[i - 1]
            if i + 1 < len(nums):
                neighbor_count += 1
                neighbor_sum += nums[i + 1]
            answer += value * neighbor_count >= neighbor_sum
        return answer
