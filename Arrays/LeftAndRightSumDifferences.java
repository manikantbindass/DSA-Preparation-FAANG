/*
 * LeetCode Problem 2574: Left and Right Sum Differences
 * Problem Number: 2574
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/left-and-right-sum-differences/
 * 
 * Given a 0-indexed integer array nums, find the array answer where answer[i] = |leftSum[i] - rightSum[i]|.
 * leftSum[i] is the sum of elements to the left of index i.
 * rightSum[i] is the sum of elements to the right of index i.
 * 
 * Example 1:
 * Input: nums = [10,4,8,3]
 * Output: [15,1,11,22]
 * Explanation: 
 * leftSum = [0,10,14,22], rightSum = [15,11,3,0]
 * answer = [|0-15|,|10-11|,|14-3|,|22-0|] = [15,1,11,22]
 * 
 * Example 2:
 * Input: nums = [1]
 * Output: [0]
 * 
 * Constraints:
 * - 1 <= nums.length <= 1000
 * - 1 <= nums[i] <= 10^5
 * 
 * Topics: Array, Prefix Sum
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - excluding the output array
 */

class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Compute total sum
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            // Remove current element from right sum
            totalSum -= nums[i];
            // Calculate absolute difference
            result[i] = Math.abs(leftSum - totalSum);
            // Add current element to left sum for next iteration
            leftSum += nums[i];
        }
        
        return result;
    }
}
