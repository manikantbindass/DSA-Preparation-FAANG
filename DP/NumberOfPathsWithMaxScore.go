// ──────────────────────────────────────────────────────────────────────
// LeetCode #1234 · Number of Paths with Max Score
// Difficulty : Hard
// Topics     : Array, Dynamic Programming, Matrix
// URL        : https://leetcode.com/problems/number-of-paths-with-max-score/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use dynamic programming from bottom-right to top-left. For each
//   cell (i,j), we consider three possible previous cells (down, right,
//   down-right) that can reach it. We maintain two DP tables: f[i][j] =
//   maximum sum from (i,j) to (n-1,n-1), and g[i][j] = number of ways to
//   achieve that maximum sum. We initialize f[n-1][n-1]=0, g[n-1][n-1]=1.
//   For each cell, we update f and g by comparing sums from the three
//   directions. After processing all cells, we add the digit value of the
//   current cell (if it's a digit) to f[i][j]. Finally, answer is f[0][0]
//   and g[0][0] (if reachable, else [0,0]).
// 
// Complexity
//   Time  : O(n^2)
//   Space : O(n^2)
// 
// Runtime  : 0 ms
// Memory   : 42.8 MB
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

func pathsWithMaxScore(board []string) []int {
    n := len(board)
    const MOD = 1000000007
    f := make([][]int, n)
    g := make([][]int, n)
    for i := 0; i < n; i++ {
        f[i] = make([]int, n)
        g[i] = make([]int, n)
        for j := 0; j < n; j++ {
            f[i][j] = -1
        }
    }
    f[n-1][n-1] = 0
    g[n-1][n-1] = 1
    
    for i := n - 1; i >= 0; i-- {
        for j := n - 1; j >= 0; j-- {
            if board[i][j] == 'X' || board[i][j] == 'S' {
                continue
            }
            // check three directions
            dirs := [][2]int{{1,0}, {0,1}, {1,1}}
            for _, d := range dirs {
                x, y := i + d[0], j + d[1]
                if x < n && y < n && f[x][y] != -1 {
                    if f[x][y] > f[i][j] {
                        f[i][j] = f[x][y]
                        g[i][j] = g[x][y]
                    } else if f[x][y] == f[i][j] {
                        g[i][j] = (g[i][j] + g[x][y]) % MOD
                    }
                }
            }
            if f[i][j] != -1 && board[i][j] >= '0' && board[i][j] <= '9' {
                f[i][j] += int(board[i][j] - '0')
            }
        }
    }
    
    if f[0][0] == -1 {
        return []int{0, 0}
    }
    return []int{f[0][0], g[0][0]}
}
