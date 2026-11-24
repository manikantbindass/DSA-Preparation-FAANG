/*
 * LeetCode Problem 3702: Maximum Subarray With Equal Products
 * Problem Number: 3702
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/maximum-subarray-with-equal-products/
 *
 * You are given an array of positive integers nums.
 *
 * An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr),
 * where:
 *
 * 	prod(arr) is the product of all elements of arr.
 * 	gcd(arr) is the GCD of all elements of arr.
 * 	lcm(arr) is the LCM of all elements of arr.
 *
 * Return the length of the longest product equivalent subarray of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,1,1,1]
 *
 * Output: 5
 *
 * Explanation: 
 *
 * The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1,
 * 1, 1]) = 2, gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.
 *
 * Example 2:
 *
 * Input: nums = [2,3,4,5,6]
 *
 * Output: 3
 *
 * Explanation: 
 *
 * The longest product equivalent subarray is [3, 4, 5].
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,1,4,5,1]
 *
 * Output: 5
 *
 *
 *
 * Constraints:
 *
 * 	2 <= nums.length <= 100
 * 	1 <= nums[i] <= 10
 *
 * Example 1:
 * Input: nums = [1,2,1,2,1,1,1]
 * Output: 5
 *
 * Example 2:
 * Input: nums = [2,3,4,5,6]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [1,2,3,1,4,5,1]
 * Output: 5
 *
 * Constraints:
 * - 2 <= nums.length <= 100
 * - 1 <= nums[i] <= 10
 *
 * Topics: Array, Math, Sliding Window, Enumeration, Number Theory
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public int maxLength(int[] nums) {
 *         int mx = 0, ml = 1;
 *         for (int x : nums) {
 *             mx = Math.max(mx, x);
 *             ml = lcm(ml, x);
 *         }
 *         int maxP = ml * mx;
 *         int n = nums.length;
 *         int ans = 0;
 *         for (int i = 0; i < n; ++i) {
 *             int p = 1, g = 0, l = 1;
 *             for (int j = i; j < n; ++j) {
 *                 p *= nums[j];
 *                 g = gcd(g, nums[j]);
 *                 l = lcm(l, nums[j]);
 *                 if (p == g * l) {
 *                     ans = Math.max(ans, j - i + 1);
 *                 }
 *                 if (p > maxP) {
 *                     break;
 *                 }
 *             }
 *         }
 *         return ans;
 *     }
 *     private int gcd(int a, int b) {
 *         while (b != 0) {
 *             int temp = b;
 *             b = a % b;
 *             a = temp;
 *         }
 *         return a;
 *     }
 *     private int lcm(int a, int b) {
 *         return a / gcd(a, b) * b;
 *     }
 * }
 */

package maximumsubarraywithequalproducts

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
