# LeetCode 1559 - Detect Cycles in 2D Grid
# Time Complexity: O(m * n) | Space Complexity: O(m * n)
from typing import List


class Solution:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        rows = len(grid)
        cols = len(grid[0])
        visited = [[False] * cols for _ in range(rows)]
        directions = (-1, 0, 1, 0, -1)

        def dfs(row: int, col: int, parent_row: int, parent_col: int) -> bool:
            visited[row][col] = True

            for index in range(4):
                next_row = row + directions[index]
                next_col = col + directions[index + 1]

                if not (0 <= next_row < rows and 0 <= next_col < cols):
                    continue

                if grid[next_row][next_col] != grid[row][col]:
                    continue

                if next_row == parent_row and next_col == parent_col:
                    continue

                if visited[next_row][next_col] or dfs(next_row, next_col, row, col):
                    return True

            return False

        for row in range(rows):
            for col in range(cols):
                if not visited[row][col] and dfs(row, col, -1, -1):
                    return True

        return False
