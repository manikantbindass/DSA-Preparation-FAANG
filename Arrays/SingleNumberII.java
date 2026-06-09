/*
 * LeetCode Problem 137: Single Number II
 * Problem Number: 137
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/single-number-ii/
 * 
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 * 
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 * 
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 * 
 * Constraints:
 * - 1 <= nums.length <= 3 * 10^4
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - Each element appears three times except for one element which appears once.
 * 
 * Topics: Array, Bit Manipulation
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        
        // Check each bit position
        for (int i = 0; i < 32; i++) {
            int bitSum = 0;
            // Count how many numbers have the ith bit set
            for (int num : nums) {
                bitSum += (num >> i) & 1;
            }
            // If the sum is not a multiple of 3, this bit belongs to the single number
            bitSum %= 3;
            result |= (bitSum << i);
        }
        
        return result;
    }
}
