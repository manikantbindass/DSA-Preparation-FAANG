/*
 * LeetCode Problem 306: Additive Number
 * Problem Number: 306
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/additive-number/
 *
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the
 * first two numbers, each subsequent number in the sequence must be the sum of the
 * preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number
 * or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1,
 * 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation: 
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation: 
 * The additive sequence is: 1, 99, 100, 199. 
 * 1 + 99 = 100, 99 + 100 = 199
 *
 *
 *
 * Constraints:
 *
 * 	1 <= num.length <= 35
 * 	num consists only of digits.
 *
 *
 *
 * Follow up: How would you handle overflow for very large input integers?
 *
 * Example 1:
 * Input: "112358"
 * Output: true
 *
 * Example 2:
 * Input: "199100199"
 * Output: true
 *
 * Constraints:
 * - 1 <= num.length <= 35
 * - num consists only of digits.
 *
 * Topics: String, Backtracking
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.3 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public boolean isAdditiveNumber(String num) {
 *         int n = num.length();
 *         for (int i =1; i < Math.min(n - 1, 19);++i) {
 *             for (int j=i+1; j < Math.min(n, i+ 19);++j) {
 *                 if (i >1 && num.charAt(0) =='0') {
 *                     break;
 *                 }
 *                 if (j-i >1 && num.charAt(i)== '0') {
 *                     continue;
 *                 }
 *                 long a= Long.parseLong(num.substring(0, i));
 *                 long b =Long.parseLong(num.substring(i, j));
 *                 if (dfs(a, b, num.substring(j))) {
 *                     return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 *     private boolean dfs(long a, long b, String num) {
 *         if ("".equals(num)) {
 *             return true;
 *         }
 *         if (a+b>0 && num.charAt(0) =='0') {
 *             return false;
 *         }
 *         for (int i= 1; i < Math.min(num.length() + 1,19);++i) {
 *             if (a+b== Long.parseLong(num.substring(0,i))) {
 *                 if (dfs(b,a + b,num.substring(i))) {
 *                     return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 * }
 */

package additivenumber

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
