/*
 * LeetCode Problem 1617: Stone Game IV
 * Problem Number: 1617
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/stone-game-iv/
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile. On each player's turn, that player
 * makes a move consisting of removing any non-zero square number of stones in the
 * pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n, return true if and only if Alice wins the game
 * otherwise return false, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have
 * any moves.
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one
 * winning the game (2 -> 1 -> 0).
 *
 * Example 3:
 *
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move,
 * removing 4 stones (4 -> 0).
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 105
 *
 * Example 1:
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 *
 * Example 2:
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 *
 * Example 3:
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 *
 * Constraints:
 * - 1 <= n <= 105
 *
 * Topics: Math, Dynamic Programming, Minimax, Game Theory, Nim Game, Sprague–Grundy Theorem, Zero-Sum Game
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 */

class Solution {
    private Boolean[] f;
    public boolean winnerSquareGame(int n) {
        f=new Boolean[n + 1];
        return dfs(n);
    }
    private boolean dfs(int i) {
        if (i <= 0) {
            return false;
        }
        if (f[i] !=null) {
            return f[i];
        }
        int k=(int) Math.sqrt(i);
        for (int j =1;j <= k; j++) {
            if (!dfs(i-j* j)) {
                return f[i] = true;
            }
        }
        return f[i] =false;
    }
}
