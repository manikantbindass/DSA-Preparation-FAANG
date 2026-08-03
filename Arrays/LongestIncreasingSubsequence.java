/*
 * LeetCode Problem 300: Longest Increasing Subsequence
 * Problem Number: 300
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
 * length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 2500
 * 	-104 <= nums[i] <= 104
 *
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time
 * complexity?
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 * - 1 <= nums.length <= 2500
 * - -104 <= nums[i] <= 104
 * - Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 *
 * Topics: Array, Binary Search, Dynamic Programming, Longest Increasing Subsequence
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i]=Math.max(f[i], f[j] + 1);
                }
            }
            ans=Math.max(ans,f[i]);
        }
        return ans;
    }
}
