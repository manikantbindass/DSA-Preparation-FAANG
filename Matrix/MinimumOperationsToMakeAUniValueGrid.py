# LeetCode 2033 - Minimum Operations to Make a Uni-Value Grid
# Time Complexity: O(m * n * log(m * n)) | Space Complexity: O(m * n)
from typing import List


class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        remainder = grid[0][0] % x
        values = []

        for row in grid:
            for value in row:
                if value % x != remainder:
                    return -1
                values.append(value)

        values.sort()
        median = values[len(values) // 2]
        operations = 0

        for value in values:
            operations += abs(value - median) // x

        return operations
