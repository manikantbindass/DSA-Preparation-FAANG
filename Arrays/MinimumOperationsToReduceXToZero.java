/*
 * LeetCode Problem 1776: Minimum Operations to Reduce X to Zero
 * Problem Number: 1776
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * You are given an integer array nums and an integer x. In one operation, you can
 * either remove the leftmost or the rightmost element from the array nums and
 * subtract its value from x. Note that this modifies the array for future
 * operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is
 * possible, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x
 * to zero.
 *
 * Example 2:
 *
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 * Example 3:
 *
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the
 * first two elements (5 operations in total) to reduce x to zero.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 104
 * 	1 <= x <= 109
 *
 * Example 1:
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 *
 * Example 2:
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - 1 <= nums[i] <= 104
 * - 1 <= x <= 109
 *
 * Topics: Array, Hash Table, Binary Search, Sliding Window, Prefix Sum
 * Time Complexity: O(log n)
 * Space Complexity: O(n)
 * Runtime: 0 ms
 * Memory: 42.8 MB
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v:nums) {
            s += v;
        }
        Map<Integer,Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            vis.putIfAbsent(t, i);
            if (vis.containsKey(t - s)) {
                mx = Math.max(mx,i-vis.get(t - s));
            }
        }
        return mx==-1 ?-1:n-mx;
    }
}
