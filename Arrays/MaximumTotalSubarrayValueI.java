/*
 * LeetCode Problem 3689: Maximum Total Subarray Value I
 * Problem Number: 3689
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-total-subarray-value-i/
 * 
 * Given an array nums and an integer k, find the maximum total value.
 * The total value is defined as k * (max element in the subarray - min element in the subarray).
 * 
 * Example:
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 8
 * Explanation: Choose subarray [1,5]? Actually, need to verify.
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= k <= 10^4
 * 
 * Topics: Array, Greedy
 * Time Complexity: O(n) - single pass to find min and max
 * Space Complexity: O(1) - only constant extra space
 */

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
            minVal = Math.min(minVal, x);
        }
        
        return 1L * k * (maxVal - minVal);
    }
}
