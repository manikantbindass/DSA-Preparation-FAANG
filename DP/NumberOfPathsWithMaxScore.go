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

func pathsWithMaxScore(board []string) []int {
    n := len(board)
    const mod = 1_000_000_007
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
            if i == n-1 && j == n-1 {
                continue
            }
            // check three predecessors: down, right, down-right
            dirs := [][2]int{{1, 0}, {0, 1}, {1, 1}}
            for _, d := range dirs {
                x, y := i+d[0], j+d[1]
                if x < n && y < n && f[x][y] != -1 && board[i][j] != 'X' {
                    if f[x][y] > f[i][j] {
                        f[i][j] = f[x][y]
                        g[i][j] = g[x][y]
                    } else if f[x][y] == f[i][j] {
                        g[i][j] = (g[i][j] + g[x][y]) % mod
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
