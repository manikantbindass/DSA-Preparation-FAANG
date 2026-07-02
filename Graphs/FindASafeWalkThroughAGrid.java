// ──────────────────────────────────────────────────────────────────────
// LeetCode #3558 · Find a Safe Walk Through a Grid
// Difficulty : Medium
// Topics     : Array, Breadth-First Search, Graph Theory, Heap (Priority Queue), Matrix, Shortest Path
// URL        : https://leetcode.com/problems/find-a-safe-walk-through-a-grid/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We model the grid as a graph where each cell is a node and edges
//   connect adjacent cells. The cost of entering a cell is the value of
//   that cell (0 or 1). We want to find the minimum total health loss (sum
//   of cell values) from (0,0) to (m-1,n-1). If that minimum loss is less
//   than the given health, we can reach safely. Since all edge weights are
//   non-negative (0 or 1), we can use a 0-1 BFS (deque) or Dijkstra's
//   algorithm. The solution uses a deque for 0-1 BFS: we maintain a
//   distance array initialized to infinity, set dist[0][0] = grid[0][0],
//   and use a deque. When exploring neighbors, if the new distance is
//   smaller, we update and push to front if the edge weight is 0, else to
//   back. This ensures we process nodes in order of increasing distance.
//   Finally, we check if dist[m-1][n-1] < health.
// 
// Complexity
//   Time  : O(m * n)
//   Space : O(m * n)
// 
// Runtime  : 0 ms
// Memory   : 42.1 MB
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
