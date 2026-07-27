/*
 * LeetCode Problem 1574: Maximum Product of Two Elements in an Array
 * Problem Number: 1574
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 *
 * Given the array of integers nums, you will choose two different indices i and j
 * of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,2]
 * Output: 12 
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will
 * get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 =
 * 12. 
 *
 * Example 2:
 *
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the
 * maximum value of (5-1)*(5-1) = 16.
 *
 * Example 3:
 *
 * Input: nums = [3,7]
 * Output: 12
 *
 *
 *
 * Constraints:
 *
 * 	2 <= nums.length <= 500
 * 	1 <= nums[i] <= 10^3
 *
 * Example 1:
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 *
 * Example 2:
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
 *
 * Example 3:
 * Input: nums = [3,7]
 * Output: 12
 *
 * Constraints:
 * - 2 <= nums.length <= 500
 * - 1 <= nums[i] <= 10^3
 *
 * Topics: Array, Sorting, Heap (Priority Queue)
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.3 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public int maxProduct(int[] nums) {
 *         int ans=0;
 *         int n=nums.length;
 *         for (int i =0; i < n; ++i) {
 *             for (int j =i + 1; j <n; ++j) {
 *                 ans= Math.max(ans, (nums[i] - 1) *(nums[j]-1));
 *             }
 *         }
 *         return ans;
 *     }
 * }
 */

package maximumproductoftwoelementsinanarray

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
