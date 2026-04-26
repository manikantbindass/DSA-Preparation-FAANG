# LeetCode 78 - Subsets
# Time Complexity: O(n * 2^n) | Space Complexity: O(1) extra excluding output
from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        answer = []

        for mask in range(1 << len(nums)):
            subset = [value for index, value in enumerate(nums) if (mask >> index) & 1]
            answer.append(subset)

        return answer
