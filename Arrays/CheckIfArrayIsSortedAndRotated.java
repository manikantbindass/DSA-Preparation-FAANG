/*
 * LeetCode Problem 1752: Check if Array Is Sorted and Rotated
 * Problem Number: 1752
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 * 
 * Given an array nums, return true if the array was originally sorted in non-decreasing order,
 * then rotated some number of positions (including zero). Otherwise, return false.
 * 
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: true
 * Explanation: [1,2,3,4,5] is the original sorted array rotated 2 positions.
 * 
 * Example 2:
 * Input: nums = [2,1,3,4]
 * Output: false
 * Explanation: There is no sorted array that can be rotated to get this array.
 * 
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: true
 * Explanation: [1,2,3] is the original sorted array rotated 0 positions.
 * 
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 100
 * 
 * Topics: Array
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int dropCount = 0;
        
        for (int i = 0; i < n; ++i) {
            // Check if current element is greater than next element (circular)
            if (nums[i] > nums[(i + 1) % n]) {
                dropCount++;
            }
        }
        
        // A valid rotated sorted array should have at most one drop
        return dropCount <= 1;
    }
}
