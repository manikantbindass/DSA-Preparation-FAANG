/*
 * LeetCode Problem 4078: Maximum Alternating Sum of Squares
 * Problem Number: 4078
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-alternating-sum-of-squares/
 *
 * You are given an integer array nums. You may rearrange the elements in any
 * order.
 *
 * The alternating score of an array arr is defined as:
 *
 * 	score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
 *
 * Return an integer denoting the maximum possible alternating score of nums after
 * rearranging its elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 *
 * Output: 12
 *
 * Explanation:
 *
 * A possible rearrangement for nums is [2,1,3], which gives the maximum
 * alternating score among all possible rearrangements.
 *
 * The alternating score is calculated as:
 *
 * score = 22 - 12 + 32 = 4 - 1 + 9 = 12
 *
 * Example 2:
 *
 * Input: nums = [1,-1,2,-2,3,-3]
 *
 * Output: 16
 *
 * Explanation:
 *
 * A possible rearrangement for nums is [-3,-1,-2,1,3,2], which gives the maximum
 * alternating score among all possible rearrangements.
 *
 * The alternating score is calculated as:
 *
 * score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	-4 * 104 <= nums[i] <= 4 * 104
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 12
 *
 * Example 2:
 * Input: nums = [1,-1,2,-2,3,-3]
 * Output: 16
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -4 * 104 <= nums[i] <= 4 * 104
 *
 * Topics: Array, Greedy, Sorting
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 43 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public long maxAlternatingSum(int[] nums) {
 *         int n = nums.length;
 *         for (int i = 0; i < n; ++i) {
 *             nums[i] *= nums[i];
 *         }
 *         Arrays.sort(nums);
 *         long ans = 0;
 *         int m = n / 2;
 *         for (int i = 0; i < m; ++i) {
 *             ans -= nums[i];
 *         }
 *         for (int i = m; i < n; ++i) {
 *             ans += nums[i];
 *         }
 *         return ans;
 *     }
 * }
 */

package maximumalternatingsumofsquares

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
