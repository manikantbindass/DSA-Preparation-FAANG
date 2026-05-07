# LeetCode 3660 - Jump Game IX
# Time Complexity: O(n) | Space Complexity: O(n)
from typing import List


class Solution:
    def maxValue(self, nums: List[int]) -> List[int]:
        n = len(nums)
        answer = [0] * n
        prefix_max = [0] * n
        prefix_max[0] = nums[0]

        for index in range(1, n):
            prefix_max[index] = max(prefix_max[index - 1], nums[index])

        suffix_min = float("inf")
        for index in range(n - 1, -1, -1):
            if prefix_max[index] > suffix_min and index + 1 < n:
                answer[index] = answer[index + 1]
            else:
                answer[index] = prefix_max[index]
            suffix_min = min(suffix_min, nums[index])

        return answer
