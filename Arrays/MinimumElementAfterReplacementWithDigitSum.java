/*
 * LeetCode Problem 3300: Minimum Element After Replacement With Digit Sum
 * Problem Number: 3300
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/minimum-element-after-replacement-with-digit-sum/
 * 
 * You are given an integer array nums. You replace each element in nums with the sum of its digits.
 * Return the minimum element in the modified array.
 * 
 * Example 1:
 * Input: nums = [10,12,13,14]
 * Output: 1
 * Explanation: After replacement: [1, 3, 4, 5], minimum is 1.
 * 
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: After replacement: [1, 2, 3, 4], minimum is 1.
 * 
 * Example 3:
 * Input: nums = [999,19,199]
 * Output: 10
 * Explanation: After replacement: [27, 10, 19], minimum is 10.
 * 
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 10^4
 * 
 * Topics: Array, Math
 * Time Complexity: O(n * log10(max(nums))) - iterate through array, compute digit sums
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public int minElement(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        
        for (int num : nums) {
            int digitSum = 0;
            int x = num;
            
            // Compute sum of digits
            while (x > 0) {
                digitSum += x % 10;
                x /= 10;
            }
            
            // Update minimum
            minSum = Math.min(minSum, digitSum);
        }
        
        return minSum;
    }
}
