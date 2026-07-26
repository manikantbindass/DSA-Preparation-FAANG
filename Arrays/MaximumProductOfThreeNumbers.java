/*
 * LeetCode Problem 628: Maximum Product of Three Numbers
 * Problem Number: 628
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/maximum-product-of-three-numbers/
 *
 * Given an integer array nums, find three numbers whose product is maximum and
 * return the maximum product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 6
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 24
 * Example 3:
 *
 * Input: nums = [-1,-2,-3]
 * Output: -6
 *
 *
 *
 * Constraints:
 *
 * 	3 <= nums.length <= 104
 * 	-1000 <= nums[i] <= 1000
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 6
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 24
 *
 * Example 3:
 * Input: nums = [-1,-2,-3]
 * Output: -6
 *
 * Constraints:
 * - 3 <= nums.length <= 104
 * - -1000 <= nums[i] <= 1000
 *
 * Topics: Array, Math, Sorting
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 43 MB
 */

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n= nums.length;
        int a = nums[n-1]*nums[n- 2]*nums[n- 3];
        int b = nums[n-1] *nums[0] *nums[1];
        return Math.max(a, b);
    }
}
