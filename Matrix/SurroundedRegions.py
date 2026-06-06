"""
LeetCode Problem 130: Surrounded Regions
Problem Number: 130
Difficulty: Medium
Link: https://leetcode.com/problems/surrounded-regions/

Given an m x n matrix board containing 'X' and 'O', capture all regions that are 
surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that 
surrounded region.

Example 1:
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Example 2:
Input: board = [["X"]]
Output: [["X"]]

Constraints:
- m == board.length
- n == board[i].length
- 1 <= m, n <= 200
- board[i][j] is 'X' or 'O'.

Topics: Array, Depth-First Search, Breadth-First Search, Union Find, Matrix
Time Complexity: O(m * n) - visit each cell once
Space Complexity: O(m * n) - for recursion stack in worst case
"""

from typing import List

class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        if not board:
            return
        
        m, n = len(board), len(board[0])
        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        def dfs(i: int, j: int) -> None:
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] != 'O':
                return
            
            # Mark this cell as safe (connected to border)
            board[i][j] = '.'
            
            # Explore all 4 directions
            for di, dj in dirs:
                dfs(i + di, j + dj)
        
        # Step 1: Mark all 'O's on the border and their connected 'O's
        # Check first and last column
        for i in range(m):
            dfs(i, 0)       # First column
            dfs(i, n - 1)   # Last column
        
        # Check first and last row
        for j in range(n):
            dfs(0, j)       # First row
            dfs(m - 1, j)   # Last row
        
        # Step 2: Flip remaining 'O's to 'X', and restore '.' back to 'O'
        for i in range(m):
            for j in range(n):
                if board[i][j] == '.':
                    board[i][j] = 'O'
                elif board[i][j] == 'O':
                    board[i][j] = 'X'
