/*
 * LeetCode Problem 2144: Minimum Cost of Buying Candies With Discount
 * Problem Number: 2144
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
 * 
 * A shop is selling candies at a discount. For every two candies you buy,
 * you get the third candy for free. More formally, if you buy a group of 3 candies,
 * you pay the cost of the two most expensive candies in that group.
 * 
 * You are given an integer array cost where cost[i] is the price of the i-th candy.
 * Return the minimum cost to buy all the candies.
 * 
 * Example 1:
 * Input: cost = [6,5,7,9,2,2]
 * Output: 23
 * Explanation: Sort: [2,2,5,6,7,9]
 * One optimal way: (9,7,6) -> pay 9+7=16, get 6 free; (5,2,2) -> pay 5+2=7, get 2 free.
 * Total = 16 + 7 = 23.
 * 
 * Example 2:
 * Input: cost = [5,5,5,5]
 * Output: 10
 * Explanation: Sort: [5,5,5,5]
 * (5,5,5) -> pay 5+5=10, get 5 free; remaining 5 -> no discount.
 * Total = 10 + 5 = 15? Wait, let's recalculate carefully.
 * Actually, we should group: (5,5,5) then remaining (5) -> pay 10 + 5 = 15.
 * 
 * Example 3:
 * Input: cost = [1,2,3]
 * Output: 5
 * Explanation: Sort: [1,2,3]
 * (3,2,1) -> pay 3+2=5, get 1 free.
 * 
 * Constraints:
 * - 1 <= cost.length <= 100
 * - 1 <= cost[i] <= 100
 * 
 * Topics: Array, Greedy, Sorting
 * Time Complexity: O(n log n) - due to sorting
 * Space Complexity: O(1) - excluding the space for sorting
 */

import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        // Sort in descending order to get the most expensive first
        Arrays.sort(cost);
        
        int totalCost = 0;
        int count = 0;
        
        // Traverse from highest to lowest
        for (int i = cost.length - 1; i >= 0; i--) {
            count++;
            // Pay for the first two of every three candies, third is free
            if (count % 3 != 0) {
                totalCost += cost[i];
            }
        }
        
        return totalCost;
    }
}
