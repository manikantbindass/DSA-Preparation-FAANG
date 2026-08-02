/*
 * LeetCode Problem 0: count-subarrays-with-even-odd-ratio-ii
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-ii/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.5 MB
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

package countsubarrayswithevenoddratioii

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
