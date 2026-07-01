// ──────────────────────────────────────────────────────────────────────
// LeetCode #2914 · Find the Safest Path in a Grid
// Difficulty : Medium
// Topics     : Array, Binary Search, Breadth-First Search, Union-Find, Heap (Priority Queue), Matrix
// URL        : https://leetcode.com/problems/find-the-safest-path-in-a-grid/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The solution uses a multi-source BFS to compute the minimum Manhattan
//   distance from each cell to the nearest thief. Then, we sort all cells
//   by this distance in descending order and use a Union-Find data
//   structure to connect cells that have a distance at least as large as
//   the current cell's distance. We process cells from highest distance to
//   lowest, and as soon as the start (0,0) and end (n-1,n-1) become
//   connected, we return the current distance, which is the maximum
//   safeness factor. If the start or end cell itself is a thief, we return
//   0 immediately.
// 
// Complexity
//   Time  : O(n^2 log n)
//   Space : O(n^2)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : grid = [[1,0,0],[0,0,0],[0,0,1]]
//     Output : 0
//     Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
//   Example 2:
//     Input  : grid = [[0,0,1],[0,0,0],[0,0,0]]
//     Output : 2
//     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//   Example 3:
//     Input  : grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
//     Output : 2
//     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
// 
// Constraints
//   · 1 <= grid.length == n <= 400
//   · grid[i].length == n
//   · grid[i][j] is either 0 or 1.
//   · There is at least one thief in the grid.
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        final int inf = 1 << 30;
        for (int[] d : dist) {
            Arrays.fill(d, inf);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == inf) {
                    dist[x][y] = dist[i][j] + 1;
                    q.offer(new int[] {x, y});
                }
            }
        }
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                t.add(new int[] {dist[i][j], i, j});
            }
        }
        t.sort((a, b) -> Integer.compare(b[0], a[0]));
        UnionFind uf = new UnionFind(n * n);
        for (int[] p : t) {
            int d = p[0], i = p[1], j = p[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d) {
                    uf.union(i * n + j, x * n + y);
                }
            }
            if (uf.find(0) == uf.find(n * n - 1)) {
                return d;
            }
        }
        return 0;
    }
}

class UnionFind {
    public int[] p;
    public int n;

    public UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        this.n = n;
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        --n;
        return true;
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
