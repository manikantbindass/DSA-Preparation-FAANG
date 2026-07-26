/*
 * LeetCode Problem 0: aggregate-two-time-series
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/aggregate-two-time-series/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.6 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public int largestInteger(int n, int s) {
 *         if (s== 0) return 0;
 *         if (s > 9 * n) return -1;
 *         StringBuilder a =new StringBuilder();
 *         for (int i = 0; i < n; i++) {
 *             int d =Math.min(9, s);
 *             a.append(d);
 *             s-= d;
 *         }
 *         return Integer.parseInt(a.toString());
 *     }
 * }
 */

package aggregatetwotimeseries

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
