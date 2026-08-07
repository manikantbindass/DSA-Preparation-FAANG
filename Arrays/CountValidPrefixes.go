/*
 * LeetCode Problem 4365: Count Valid Prefixes
 * Problem Number: 4365
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/count-valid-prefixes/
 *
 * You are given a binary string s.
 *
 * A prefix of s is considered valid if its characters can be rearranged to form an
 * alternating string.
 *
 * Return the number of valid prefixes of s.
 *
 * A string is considered alternating if no two adjacent characters are equal.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00101"
 *
 * Output: 3
 *
 * Explanation:
 *
 * The valid prefixes are:
 *
 * 	"0": It is already an alternating string.
 * 	"001": It can be rearranged into "010", which is an alternating string.
 * 	"00101": It can be rearranged into "01010", which is an alternating string.
 *
 * Thus, the answer is 3.
 *
 * Example 2:
 *
 * Input: s = "101"
 *
 * Output: 3
 *
 * Explanation:
 *
 * All prefixes of s = "101" are already alternating strings. Thus, the answer is
 * 3.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 100
 * 	s consists only of '0' and '1'.
 *
 * Example 1:
 * Input: s = "00101"
 * Output: 3
 *
 * Example 2:
 * Input: s = "101"
 * Output: 3
 *
 * Constraints:
 * - 1 <= s.length <= 100
 * - s consists only of '0' and '1'.
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public int countValidPrefixes(String s) {
 *         int z =0,o=0,a = 0;
 *         for (int i =0; i<s.length(); i++) {
 *             if (s.charAt(i)== '0') z++;
 *             else o++;
 *             if (Math.abs(z-o) <= 1) a++;
 *         }
 *         return a;
 *     }
 * }
 */

package countvalidprefixes

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
