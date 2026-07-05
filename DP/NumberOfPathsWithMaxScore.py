# ──────────────────────────────────────────────────────────────────────
# LeetCode #1234 · Number of Paths with Max Score
# Difficulty : Hard
# Topics     : Array, Dynamic Programming, Matrix
# URL        : https://leetcode.com/problems/number-of-paths-with-max-score/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use dynamic programming from bottom-right to top-left. For each
#   cell (i,j), we consider three possible previous cells (down, right,
#   down-right) that can reach it. We maintain two DP tables: f[i][j] =
#   maximum sum from (i,j) to (n-1,n-1), and g[i][j] = number of ways to
#   achieve that maximum sum. We initialize f[n-1][n-1]=0, g[n-1][n-1]=1.
#   For each cell, we update f and g by comparing sums from the three
#   directions. After processing all cells, we add the digit value of the
#   current cell (if it's a digit) to f[i][j]. Finally, answer is f[0][0]
#   and g[0][0] (if reachable, else [0,0]).
# 
# Complexity
#   Time  : O(n^2)
#   Space : O(n^2)
# 
# Runtime  : 0 ms
# Memory   : 42.8 MB
# 
# Examples
#   Example 1:
#     Input  : board = ["E23","2X2","12S"]
#     Output : [7,1]
#   Example 2:
#     Input  : board = ["E12","1X1","21S"]
#     Output : [4,2]
#   Example 3:
#     Input  : board = ["E11","XXX","11S"]
#     Output : [0,0]
# 
# Constraints
#   · 2 <= board.length == board[i].length <= 100
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def pathsWithMaxScore(self, board: List[str]) -> List[int]:
        n = len(board)
        MOD = 10**9 + 7
        f = [[-1] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        f[n-1][n-1] = 0
        g[n-1][n-1] = 1
        
        for i in range(n-1, -1, -1):
            for j in range(n-1, -1, -1):
                if board[i][j] == 'X' or board[i][j] == 'S':
                    continue
                # check three directions
                for dx, dy in [(1,0), (0,1), (1,1)]:
                    x, y = i + dx, j + dy
                    if x < n and y < n and f[x][y] != -1:
                        if f[x][y] > f[i][j]:
                            f[i][j] = f[x][y]
                            g[i][j] = g[x][y]
                        elif f[x][y] == f[i][j]:
                            g[i][j] = (g[i][j] + g[x][y]) % MOD
                if f[i][j] != -1 and board[i][j].isdigit():
                    f[i][j] += int(board[i][j])
        
        if f[0][0] == -1:
            return [0, 0]
        return [f[0][0], g[0][0]]
