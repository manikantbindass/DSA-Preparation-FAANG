/*
 * LeetCode Problem 4245: Count Commas in Range
 * Problem Number: 4245
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/count-commas-in-range/
 *
 * You are given an integer n.
 *
 * Return the total number of commas used when writing all integers from [1, n]
 * (inclusive) in standard number formatting.
 *
 * In standard formatting:
 *
 * 	A comma is inserted after every three digits from the right.
 * 	Numbers with fewer than 4 digits contain no commas.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1002
 *
 * Output: 3
 *
 * Explanation:
 *
 * The numbers "1,000", "1,001", and "1,002" each contain one comma, giving a total
 * of 3.
 *
 * Example 2:
 *
 * Input: n = 998
 *
 * Output: 0
 *
 * Explanation:
 *
 * All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are
 * used.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 105
 *
 * Example 1:
 * Input: n = 1002
 * Output: 3
 *
 * Example 2:
 * Input: n = 998
 * Output: 0
 *
 * Constraints:
 * - 1 <= n <= 105
 *
 * Topics: Math
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public int countCommas(int n) {
 *         return Math.max(0, n - 999);
 *     }
 * }
 */

package countcommasinrange

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
