# LeetCode 2553 - Separate the Digits in an Array
# Time Complexity: O(n * d) | Space Complexity: O(n * d)
from typing import List


class Solution:
    def separateDigits(self, nums: List[int]) -> List[int]:
        digits: List[int] = []

        for value in nums:
            current: List[int] = []
            while value > 0:
                current.append(value % 10)
                value //= 10
            current.reverse()
            digits.extend(current)

        return digits
