/*
 * LeetCode Problem 4020: Lexicographically Smallest Permutation Greater Than Target
 * Problem Number: 4020
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/
 *
 * You are given two strings s and target, both having length n, consisting of
 * lowercase English letters.
 *
 * Return the lexicographically smallest permutation of s that is strictly greater
 * than target. If no permutation of s is lexicographically strictly greater than
 * target, return an empty string.
 *
 * A string a is lexicographically strictly greater than a string b (of the same
 * length) if in the first position where a and b differ, string a has a letter
 * that appears later in the alphabet than the corresponding letter in b.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", target = "bba"
 *
 * Output: "bca"
 *
 * Explanation:
 *
 * 	The permutations of s (in lexicographical order) are "abc", "acb", "bac",
 * "bca", "cab", and "cba".
 * 	The lexicographically smallest permutation that is strictly greater than target
 * is "bca".
 *
 * Example 2:
 *
 * Input: s = "leet", target = "code"
 *
 * Output: "eelt"
 *
 * Explanation:
 *
 * 	The permutations of s (in lexicographical order) are "eelt", "eetl", "elet",
 * "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
 * 	The lexicographically smallest permutation that is strictly greater than target
 * is "eelt".
 *
 * Example 3:
 *
 * Input: s = "baba", target = "bbaa"
 *
 * Output: ""
 *
 * Explanation:
 *
 * 	The permutations of s (in lexicographical order) are "aabb", "abab", "abba",
 * "baab", "baba", and "bbaa".
 * 	None of them is lexicographically strictly greater than target. Therefore, the
 * answer is "".
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length == target.length <= 300
 * 	s and target consist of only lowercase English letters.
 *
 * Example 1:
 * Input: s = "abc", target = "bba"
 * Output: "bca"
 *
 * Example 2:
 * Input: s = "leet", target = "code"
 * Output: "eelt"
 *
 * Example 3:
 * Input: s = "baba", target = "bbaa"
 * Output: ""
 *
 * Constraints:
 * - 1 <= s.length == target.length <= 300
 * - s and target consist of only lowercase English letters.
 *
 * Topics: Hash Table, String, Greedy, Counting, Enumeration
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public String lexGreaterPermutation(String stark, String thanos) {
 *         int n = stark.length();
 *         int[] hydra = new int[26];
 *         for (int i = 0; i < n; i++) hydra[stark.charAt(i) - 'a']++;
 *         int[][] vault = new int[n + 1][];
 *         vault[0] = hydra.clone();
 *         int fury = 0;
 *         while (fury < n) {
 *             int idx = thanos.charAt(fury) - 'a';
 *             if (hydra[idx] > 0) {
 *                 hydra[idx]--;
 *                 fury++;
 *                 vault[fury]= hydra.clone();
 *             } else break;
 *         }
 *         int start = (fury==n) ? n - 1 : fury;
 *         int pos = -1, pick =-1;
 *         int[] snap = null;
 *         for (int j = start; j >= 0; j--) {
 *             int[] st = vault[j];
 *             int tc =thanos.charAt(j) - 'a';
 *             int found = -1;
 *             for (int k = tc + 1; k < 26; k++) {
 *                 if (st[k] > 0) { found = k; break; }
 *             }
 *             if (found != -1) {
 *                 pos = j;
 *                 pick = found;
 *                 snap = st;
 *                 break;
 *             }
 *         }
 *         if (pos == -1) return "";
 *         StringBuilder wanda = new StringBuilder();
 *         wanda.append(thanos, 0, pos);
 *         wanda.append((char) ('a' + pick));
 *         int[] rem = snap.clone();
 *         rem[pick]--;
 *         for (int k = 0; k < 26; k++) {
 *             for (int c = 0; c < rem[k]; c++) wanda.append((char) ('a' + k));
 *         }
 *         return wanda.toString();
 *     }
 * }
 */

package lexicographicallysmallestpermutationgreaterthantarget

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
