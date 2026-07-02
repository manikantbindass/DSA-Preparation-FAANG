// ──────────────────────────────────────────────────────────────────────
// LeetCode #3558 · Find a Safe Walk Through a Grid
// Difficulty : Medium
// Topics     : Array, Breadth-First Search, Graph Theory, Heap (Priority Queue), Matrix, Shortest Path
// URL        : https://leetcode.com/problems/find-a-safe-walk-through-a-grid/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We model the problem as a shortest path problem where each cell has a
//   cost equal to its value (0 or 1). The goal is to find the minimum
//   total cost (number of unsafe cells visited) from (0,0) to (m-1,n-1).
//   If that minimum cost is less than the given health, we can reach
//   safely. We use a 0-1 BFS (or Dijkstra with a deque) because edge
//   weights are 0 or 1. The algorithm maintains a distance matrix
//   initialized to infinity, sets dist[0][0] = grid[0][0], and uses a
//   deque to process cells. For each neighbor, if the new distance is
//   smaller, we update and push the neighbor to the front if the edge
//   weight is 0, else to the back. This ensures we always process cells
//   with smaller distances first, achieving O(m*n) time.
// 
// Complexity
//   Time  : O(m * n)
//   Space : O(m * n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
//     Output : true
//   Example 2:
//     Input  : grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
//     Output : false
//   Example 3:
//     Input  : grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
//     Output : true
// 
// Constraints
//   · m == grid.length
//   · n == grid[i].length
//   · 1 <= m, n <= 50
//   · 2 <= m * n
//   · 1 <= health <= m + n
//   · grid[i][j] is either 0 or 1.
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = grid.get(0).get(0);
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerFirst(new int[]{0, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int nd = dist[x][y] + grid.get(nx).get(ny);
                    if (nd < dist[nx][ny]) {
                        dist[nx][ny] = nd;
                        if (grid.get(nx).get(ny) == 0) {
                            dq.offerFirst(new int[]{nx, ny});
                        } else {
                            dq.offerLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
}
