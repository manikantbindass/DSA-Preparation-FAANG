// ──────────────────────────────────────────────────────────────────────
// LeetCode #200 · Number of Islands
// Difficulty : Medium
// Topics     : Array, Depth-First Search, Breadth-First Search, Union-Find, Matrix
// URL        : https://leetcode.com/problems/number-of-islands/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We traverse the grid cell by cell. When we encounter a '1', we
//   increment the island count and perform a depth-first search (DFS) to
//   mark all connected land cells as visited by setting them to '0'. This
//   ensures each island is counted exactly once. The DFS explores the four
//   cardinal directions (up, down, left, right) recursively.
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
//     Input  : grid = [
//     Output : 1
//   Example 2:
//     Input  : grid = [
//     Output : 3
// 
// Constraints
//   · m == grid.length
//   · n == grid[i].length
//   · 1 <= m, n <= 300
//   · grid[i][j] is '0' or '1'.
// ──────────────────────────────────────────────────────────────────────

class Solution {
    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        grid[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(x, y);
            }
        }
    }
}
