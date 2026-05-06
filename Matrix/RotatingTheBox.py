# LeetCode 1861 - Rotating the Box
# Time Complexity: O(m * n) | Space Complexity: O(m * n)
from collections import deque
from typing import Deque, List


class Solution:
    def rotateTheBox(self, boxGrid: List[List[str]]) -> List[List[str]]:
        rows = len(boxGrid)
        cols = len(boxGrid[0])
        rotated = [[''] * rows for _ in range(cols)]

        for row in range(rows):
            for col in range(cols):
                rotated[col][rows - row - 1] = boxGrid[row][col]

        for col in range(rows):
            empty_rows: Deque[int] = deque()

            for row in range(cols - 1, -1, -1):
                if rotated[row][col] == '*':
                    empty_rows.clear()
                elif rotated[row][col] == '.':
                    empty_rows.append(row)
                elif empty_rows:
                    rotated[empty_rows.popleft()][col] = '#'
                    rotated[row][col] = '.'
                    empty_rows.append(row)

        return rotated
