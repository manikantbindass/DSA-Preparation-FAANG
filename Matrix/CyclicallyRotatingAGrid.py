# LeetCode 1914 - Cyclically Rotating a Grid
# Time Complexity: O(m * n) | Space Complexity: O(m * n)
from typing import List


class Solution:
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        rows = len(grid)
        cols = len(grid[0])

        def rotate_layer(layer: int, shift: int) -> None:
            values = []

            for col in range(layer, cols - layer - 1):
                values.append(grid[layer][col])
            for row in range(layer, rows - layer - 1):
                values.append(grid[row][cols - layer - 1])
            for col in range(cols - layer - 1, layer, -1):
                values.append(grid[rows - layer - 1][col])
            for row in range(rows - layer - 1, layer, -1):
                values.append(grid[row][layer])

            length = len(values)
            shift %= length
            if shift == 0:
                return

            for col in range(layer, cols - layer - 1):
                grid[layer][col] = values[shift % length]
                shift += 1
            for row in range(layer, rows - layer - 1):
                grid[row][cols - layer - 1] = values[shift % length]
                shift += 1
            for col in range(cols - layer - 1, layer, -1):
                grid[rows - layer - 1][col] = values[shift % length]
                shift += 1
            for row in range(rows - layer - 1, layer, -1):
                grid[row][layer] = values[shift % length]
                shift += 1

        for layer in range(min(rows, cols) // 2):
            rotate_layer(layer, k)

        return grid
