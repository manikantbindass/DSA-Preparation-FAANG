# ──────────────────────────────────────────────────────────────────────
# LeetCode #200 · Number of Islands
# Difficulty : Medium
# Topics     : Array, Depth-First Search, Breadth-First Search, Union-Find, Matrix
# URL        : https://leetcode.com/problems/number-of-islands/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We traverse the grid cell by cell. When we encounter a '1', we
#   increment the island count and perform a depth-first search (DFS) to
#   mark all connected land cells as visited by setting them to '0'. This
#   ensures each island is counted exactly once. The DFS explores the four
#   cardinal directions (up, down, left, right) recursively.
# 
# Complexity
#   Time  : O(m * n)
#   Space : O(m * n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : grid = [
#     Output : 1
#   Example 2:
#     Input  : grid = [
#     Output : 3
# 
# Constraints
#   · m == grid.length
#   · n == grid[i].length
#   · 1 <= m, n <= 300
#   · grid[i][j] is '0' or '1'.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        m, n = len(grid), len(grid[0])
        ans = 0
        
        def dfs(i, j):
            if i < 0 or i >= m or j < 0 or j >= n or grid[i][j] == '0':
                return
            grid[i][j] = '0'
            dfs(i-1, j)
            dfs(i+1, j)
            dfs(i, j-1)
            dfs(i, j+1)
        
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    dfs(i, j)
                    ans += 1
        return ans
