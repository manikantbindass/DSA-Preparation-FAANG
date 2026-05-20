# LeetCode 977 - Squares of a Sorted Array
# Time Complexity: O(n) | Space Complexity: O(n)
from typing import List


class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        length = len(nums)
        answer = [0] * length
        left = 0
        right = length - 1

        for index in range(length - 1, -1, -1):
            left_square = nums[left] * nums[left]
            right_square = nums[right] * nums[right]

            if left_square > right_square:
                answer[index] = left_square
                left += 1
            else:
                answer[index] = right_square
                right -= 1

        return answer
