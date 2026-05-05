# LeetCode 118 - Pascal's Triangle
# Time Complexity: O(numRows^2) | Space Complexity: O(numRows^2)
from typing import List


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        answer: List[List[int]] = []

        for row in range(numRows):
            current = [1]

            for col in range(1, row):
                current.append(answer[row - 1][col - 1] + answer[row - 1][col])

            if row > 0:
                current.append(1)

            answer.append(current)

        return answer
