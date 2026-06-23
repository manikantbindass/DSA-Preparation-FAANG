/*
 * LeetCode Problem 174: Dungeon Game
 * Problem Number: 174
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/dungeon-game/
 * 
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially
 * positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * 
 * The knight has an initial health point represented by a positive integer. If at any point his
 * health point drops to 0 or below, he dies immediately.
 * 
 * Some rooms contain demons (negative integers), which reduce the knight's health. Other rooms
 * contain magic orbs (positive integers) that increase the knight's health.
 * 
 * Determine the knight's minimum initial health so that he can rescue the princess.
 * 
 * Example 1:
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The optimal path is right -> right -> down -> down.
 * 
 * Example 2:
 * Input: dungeon = [[0]]
 * Output: 1
 * 
 * Constraints:
 * - m == dungeon.length
 * - n == dungeon[i].length
 * - 1 <= m, n <= 200
 * - -1000 <= dungeon[i][j] <= 1000
 * 
 * Topics: Array, Dynamic Programming, Matrix
 * Time Complexity: O(m * n) - single pass through the grid
 * Space Complexity: O(m * n) - for the DP table (can be optimized to O(n))
 */

import java.util.Arrays;

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        // dp[i][j] = minimum health needed to reach the princess from cell (i, j)
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize dp table with infinity
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Base cases: We need 1 health to enter the princess room (bottom-right)
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        // Fill dp table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Minimum health needed to go from (i, j) to the princess
                int minHealth = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                // Health must be at least 1
                dp[i][j] = Math.max(1, minHealth);
            }
        }
        
        return dp[0][0];
    }
}
