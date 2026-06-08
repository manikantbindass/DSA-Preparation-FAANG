/*
 * LeetCode Problem 135: Candy
 * Problem Number: 135
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/candy/
 * 
 * There are n children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * - Each child must have at least one candy.
 * - Children with a higher rating get more candies than their neighbors.
 * 
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * 
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * 
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * 
 * Constraints:
 * - n == ratings.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= ratings[i] <= 2 * 10^4
 * 
 * Topics: Array, Greedy
 * Time Complexity: O(n) - two passes through the array
 * Space Complexity: O(n) - for the left and right arrays
 */

import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];
        
        // Each child gets at least 1 candy
        Arrays.fill(leftToRight, 1);
        Arrays.fill(rightToLeft, 1);
        
        // Left to right pass: ensure right child gets more if rating is higher
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                leftToRight[i] = leftToRight[i - 1] + 1;
            }
        }
        
        // Right to left pass: ensure left child gets more if rating is higher
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            }
        }
        
        // Sum the maximum of both passes for each child
        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += Math.max(leftToRight[i], rightToLeft[i]);
        }
        
        return totalCandies;
    }
}
