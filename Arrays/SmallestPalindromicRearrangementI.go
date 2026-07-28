/*
 * LeetCode Problem 3812: Smallest Palindromic Rearrangement I
 * Problem Number: 3812
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/smallest-palindromic-rearrangement-i/
 *
 * You are given a palindromic string s.
 *
 * Return the lexicographically smallest palindromic permutation of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "z"
 *
 * Output: "z"
 *
 * Explanation:
 *
 * A string of only one character is already the lexicographically smallest
 * palindrome.
 *
 * Example 2:
 *
 * Input: s = "babab"
 *
 * Output: "abbba"
 *
 * Explanation:
 *
 * Rearranging "babab" &rarr; "abbba" gives the smallest lexicographic palindrome.
 *
 * Example 3:
 *
 * Input: s = "daccad"
 *
 * Output: "acddca"
 *
 * Explanation:
 *
 * Rearranging "daccad" &rarr; "acddca" gives the smallest lexicographic
 * palindrome.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 105
 * 	s consists of lowercase English letters.
 * 	s is guaranteed to be palindromic.
 *
 * Example 1:
 * Input: s = "z"
 * Output: "z"
 *
 * Example 2:
 * Input: s = "babab"
 * Output: "abbba"
 *
 * Example 3:
 * Input: s = "daccad"
 * Output: "acddca"
 *
 * Constraints:
 * - 1 <= s.length <= 105
 * - s consists of lowercase English letters.
 * - s is guaranteed to be palindromic.
 *
 * Topics: String, Sorting, Counting Sort
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 20 ms
 * Memory: 48.1 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public String smallestPalindrome(String s) {
 *         int[] cnt=new int[26];
 *         for (char c : s.toCharArray()) {
 *             cnt[c -'a']++;
 *         }
 * 
 *         StringBuilder t = new StringBuilder();
 *         String ch = "";
 * 
 *         for (char c='a'; c <= 'z'; c++) {
 *             int idx =c- 'a';
 *             int v=cnt[idx] / 2;
 *             if (v >0) {
 *                 t.append(String.valueOf(c).repeat(v));
 *             }
 *             cnt[idx] -= v*2;
 *             if (cnt[idx] == 1) {
 *                 ch =String.valueOf(c);
 *             }
 *         }
 * 
 *         String ans =t.toString();
 *         ans =ans +ch+new StringBuilder(ans).reverse();
 *         return ans;
 *     }
 * }
 */

package smallestpalindromicrearrangementi

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
