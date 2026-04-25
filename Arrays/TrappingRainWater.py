# LeetCode 42 - Trapping Rain Water
# Time Complexity: O(n) | Space Complexity: O(n)
from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n == 0:
            return 0

        left = [0] * n
        right = [0] * n
        left[0] = height[0]
        right[n - 1] = height[n - 1]

        for i in range(1, n):
            left[i] = max(left[i - 1], height[i])
            right[n - i - 1] = max(right[n - i], height[n - i - 1])

        trapped = 0
        for i, value in enumerate(height):
            trapped += min(left[i], right[i]) - value

        return trapped
