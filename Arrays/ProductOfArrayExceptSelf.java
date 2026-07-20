/*
 * LeetCode Problem 238: Product of Array Except Self
 * Problem Number: 238
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal
 * to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 *
 * Constraints:
 *
 * 	2 <= nums.length <= 105
 * 	-30 <= nums[i] <= 30
 * 	The input is generated such that answer[i] is guaranteed to fit in a 32-bit
 * integer.
 *
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output
 * array does not count as extra space for space complexity analysis.)
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * - 2 <= nums.length <= 105
 * - -30 <= nums[i] <= 30
 * - The input is generated such that answer[i] is
 *
 * Topics: Array, Prefix Sum
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans =new int[n];
        for (int i= 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *=nums[i];
        }
        for (int i= n -1, right= 1; i>= 0;--i) {
            ans[i] *=right;
            right*= nums[i];
        }
        return ans;
    }
}
