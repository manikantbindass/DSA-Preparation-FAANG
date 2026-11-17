/*
 * LeetCode Problem 322: Coin Change
 * Problem Number: 322
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/coin-change/
 *
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that
 * amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 	1 <= coins.length <= 12
 * 	1 <= coins[i] <= 231 - 1
 * 	0 <= amount <= 104
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Constraints:
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 231 - 1
 * - 0 <= amount <= 104
 *
 * Topics: Array, Dynamic Programming, Breadth-First Search, Knapsack Problem, Complete Knapsack
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 */

class Solution {
    public int coinChange(int[] coins, int amount) {
        final int inf = 1 << 30;
        int n = amount;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = Math.min(f[j], f[j - x] + 1);
            }
        }
        return f[n] >= inf ? -1 : f[n];
    }
}
