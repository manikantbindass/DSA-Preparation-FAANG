/*
 * LeetCode Problem 0: maximize-pair-strength-using-gcd
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximize-pair-strength-using-gcd/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public long maxPairStrength(int[] nums) {
 *         long ans =0;
 *         for (int i= 0; i < nums.length;i++) {
 *             for (int j= i + 1; j <nums.length;j++) {
 *                 long g= gcd(nums[i], nums[j]);
 *                 long v =(long) nums[i] * nums[j] /(g * g);
 *                 if (v> ans) ans = v;
 *             }
 *         }
 *         return ans;
 *     }
 *     private long gcd(long a,long b){
 *         while (b !=0) {
 *             long t= a % b;
 *             a= b;
 *             b =t;
 *         }
 *         return a;
 *     }
 * }
 */

package maximizepairstrengthusinggcd

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
