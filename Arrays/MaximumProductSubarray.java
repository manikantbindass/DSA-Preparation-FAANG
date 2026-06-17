/*
 * LeetCode Problem 152: Maximum Product Subarray
 * Problem Number: 152
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-product-subarray/
 * 
 * Given an integer array nums, find a contiguous non-empty subarray within the array
 * that has the largest product, and return the product.
 * 
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * 
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -10 <= nums[i] <= 10
 * 
 * Topics: Array, Dynamic Programming
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            // If num is negative, max and min swap
            if (num < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }
            
            // Update current max and min
            currentMax = Math.max(num, currentMax * num);
            currentMin = Math.min(num, currentMin * num);
            
            // Update global maximum
            maxProduct = Math.max(maxProduct, currentMax);
        }
        
        return maxProduct;
    }
}
