# ──────────────────────────────────────────────────────────────────────
# LeetCode #1234 · Number of Paths with Max Score
# Difficulty : Hard
# Topics     : Array, Dynamic Programming, Matrix
# URL        : https://leetcode.com/problems/number-of-paths-with-max-score/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use dynamic programming from bottom-right to top-left. For each
#   cell, we consider three possible previous moves (up, left, up-left)
#   and track the maximum sum and number of ways to reach that cell. We
#   initialize the start cell 'S' with sum 0 and 1 way. For each cell, we
#   update from its three predecessors (down, right, down-right) if they
#   are valid. After processing all predecessors, we add the cell's
#   numeric value (if any) to the sum. The answer is the values at the
#   top-left 'E' cell. If unreachable, return [0,0].
# 
# Complexity
#   Time  : O(n^2)
#   Space : O(n^2)
# 
# Runtime  : 
# Memory   : 
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
        # f[i][j] = max sum from (i,j) to bottom-right, -1 if unreachable
        f = [[-1] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]  # number of ways
        f[n-1][n-1] = 0
        g[n-1][n-1] = 1
        
        for i in range(n-1, -1, -1):
            for j in range(n-1, -1, -1):
                # skip start cell (already initialized)
                if i == n-1 and j == n-1:
                    continue
                # check three predecessors: down, right, down-right
                for dx, dy in [(1,0), (0,1), (1,1)]:
                    x, y = i + dx, j + dy
                    if x < n and y < n and f[x][y] != -1 and board[i][j] != 'X':
                        if f[x][y] > f[i][j]:
                            f[i][j] = f[x][y]
                            g[i][j] = g[x][y]
                        elif f[x][y] == f[i][j]:
                            g[i][j] = (g[i][j] + g[x][y]) % MOD
                # add current cell's numeric value if applicable
                if f[i][j] != -1 and board[i][j].isdigit():
                    f[i][j] += int(board[i][j])
        
        if f[0][0] == -1:
            return [0, 0]
        return [f[0][0], g[0][0]]
