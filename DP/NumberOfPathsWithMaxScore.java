// ──────────────────────────────────────────────────────────────────────
// LeetCode #1234 · Number of Paths with Max Score
// Difficulty : Hard
// Topics     : Array, Dynamic Programming, Matrix
// URL        : https://leetcode.com/problems/number-of-paths-with-max-score/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use dynamic programming from bottom-right to top-left. For each
//   cell, we consider three possible previous moves (up, left, up-left)
//   and track the maximum sum and number of ways to reach that cell. We
//   initialize the start cell 'S' with sum 0 and 1 way. For each cell, we
//   update from its three predecessors (down, right, down-right) if they
//   are valid. After processing all predecessors, we add the cell's
//   numeric value (if any) to the sum. The answer is the values at the
//   top-left 'E' cell. If unreachable, return [0,0].
// 
// Complexity
//   Time  : O(n^2)
//   Space : O(n^2)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : board = ["E23","2X2","12S"]
//     Output : [7,1]
//   Example 2:
//     Input  : board = ["E12","1X1","21S"]
//     Output : [4,2]
//   Example 3:
//     Input  : board = ["E11","XXX","11S"]
//     Output : [0,0]
// 
// Constraints
//   · 2 <= board.length == board[i].length <= 100
// ──────────────────────────────────────────────────────────────────────

class Solution {
    private List<String> board;
    private int n;
    private int[][] f;
    private int[][] g;
    private final int mod = (int) 1e9 + 7;
    
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();
        this.board = board;
        f = new int[n][n];
        g = new int[n][n];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        f[n - 1][n - 1] = 0;
        g[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                update(i, j, i + 1, j);
                update(i, j, i, j + 1);
                update(i, j, i + 1, j + 1);
                if (f[i][j] != -1) {
                    char c = board.get(i).charAt(j);
                    if (c >= '0' && c <= '9') {
                        f[i][j] += (c - '0');
                    }
                }
            }
        }
        int[] ans = new int[2];
        if (f[0][0] != -1) {
            ans[0] = f[0][0];
            ans[1] = g[0][0];
        }
        return ans;
    }

    private void update(int i, int j, int x, int y) {
        if (x >= n || y >= n || f[x][y] == -1 || board.get(i).charAt(j) == 'X'
            || board.get(i).charAt(j) == 'S') {
            return;
        }
        if (f[x][y] > f[i][j]) {
            f[i][j] = f[x][y];
            g[i][j] = g[x][y];
        } else if (f[x][y] == f[i][j]) {
            g[i][j] = (g[i][j] + g[x][y]) % mod;
        }
    }
}
