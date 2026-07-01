# ──────────────────────────────────────────────────────────────────────
# LeetCode #2914 · Find the Safest Path in a Grid
# Difficulty : Medium
# Topics     : Array, Binary Search, Breadth-First Search, Union-Find, Heap (Priority Queue), Matrix
# URL        : https://leetcode.com/problems/find-the-safest-path-in-a-grid/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The solution uses a multi-source BFS to compute the minimum Manhattan
#   distance from each cell to the nearest thief. Then, we sort all cells
#   by this distance in descending order and use a Union-Find data
#   structure to connect cells that have a distance at least as large as
#   the current cell's distance. We process cells from highest distance to
#   lowest, and as soon as the start (0,0) and end (n-1,n-1) become
#   connected, we return the current distance, which is the maximum
#   safeness factor. If the start or end cell itself is a thief, we return
#   0 immediately.
# 
# Complexity
#   Time  : O(n^2 log n)
#   Space : O(n^2)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : grid = [[1,0,0],[0,0,0],[0,0,1]]
#     Output : 0
#     Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
#   Example 2:
#     Input  : grid = [[0,0,1],[0,0,0],[0,0,0]]
#     Output : 2
#     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
#   Example 3:
#     Input  : grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
#     Output : 2
#     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
# 
# Constraints
#   · 1 <= grid.length == n <= 400
#   · grid[i].length == n
#   · grid[i][j] is either 0 or 1.
#   · There is at least one thief in the grid.
# ──────────────────────────────────────────────────────────────────────

from collections import deque

class Solution:
    def maximumSafenessFactor(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if grid[0][0] == 1 or grid[n-1][n-1] == 1:
            return 0
        
        # Multi-source BFS to compute distance to nearest thief
        dist = [[float('inf')] * n for _ in range(n)]
        q = deque()
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    dist[i][j] = 0
                    q.append((i, j))
        
        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        while q:
            i, j = q.popleft()
            for di, dj in dirs:
                x, y = i + di, j + dj
                if 0 <= x < n and 0 <= y < n and dist[x][y] == float('inf'):
                    dist[x][y] = dist[i][j] + 1
                    q.append((x, y))
        
        # Sort cells by distance descending
        cells = [(dist[i][j], i, j) for i in range(n) for j in range(n)]
        cells.sort(reverse=True)
        
        # Union-Find
        parent = list(range(n * n))
        def find(x):
            while parent[x] != x:
                parent[x] = parent[parent[x]]
                x = parent[x]
            return x
        def union(a, b):
            ra, rb = find(a), find(b)
            if ra != rb:
                parent[ra] = rb
                return True
            return False
        
        for d, i, j in cells:
            for di, dj in dirs:
                x, y = i + di, j + dj
                if 0 <= x < n and 0 <= y < n and dist[x][y] >= d:
                    union(i * n + j, x * n + y)
            if find(0) == find(n * n - 1):
                return d
        return 0
