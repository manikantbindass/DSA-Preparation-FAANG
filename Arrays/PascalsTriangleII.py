# LeetCode 119 - Pascal's Triangle II
# Time Complexity: O(rowIndex^2) | Space Complexity: O(rowIndex)
from typing import List


class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        row = [1] * (rowIndex + 1)

        for current_row in range(2, rowIndex + 1):
            for col in range(current_row - 1, 0, -1):
                row[col] += row[col - 1]

        return row
