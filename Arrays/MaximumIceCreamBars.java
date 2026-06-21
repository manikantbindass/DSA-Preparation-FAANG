/*
 * LeetCode Problem 1833: Maximum Ice Cream Bars
 * Problem Number: 1833
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-ice-cream-bars/
 * 
 * You are given an array costs where costs[i] is the price of the i-th ice cream bar.
 * You have an integer coins representing the total amount of coins you have.
 * Return the maximum number of ice cream bars you can buy.
 * 
 * Example 1:
 * Input: costs = [1,3,2,4,1], coins = 7
 * Output: 4
 * Explanation: You can buy bars with costs [1,1,2,3] for a total of 7 coins.
 * 
 * Example 2:
 * Input: costs = [10,6,8,7,7,8], coins = 5
 * Output: 0
 * 
 * Example 3:
 * Input: costs = [1,6,3,1,2,5], coins = 20
 * Output: 6
 * 
 * Constraints:
 * - 1 <= costs.length <= 10^5
 * - 1 <= costs[i] <= 10^5
 * - 1 <= coins <= 10^8
 * 
 * Topics: Array, Greedy, Sorting
 * Time Complexity: O(n log n) - due to sorting
 * Space Complexity: O(1) - excluding the space for sorting
 */

import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Sort costs in ascending order to buy cheapest first
        Arrays.sort(costs);
        
        int count = 0;
        for (int cost : costs) {
            if (coins < cost) {
                break;
            }
            coins -= cost;
            count++;
        }
        
        return count;
    }
}
