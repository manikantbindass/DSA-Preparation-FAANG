/*
 * LeetCode Problem 274: H-Index
 * Problem Number: 274
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/h-index/
 *
 * Given an array of integers citations where citations[i] is the number of
 * citations a researcher received for their ith paper, return the researcher's
 * h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as
 * the maximum value of h such that the given researcher has published at least h
 * papers that have each been cited at least h times.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of
 * them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the
 * remaining two with no more than 3 citations each, their h-index is 3.
 *
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 * 	n == citations.length
 * 	1 <= n <= 5000
 * 	0 <= citations[i] <= 1000
 *
 * Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 *
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 *
 * Constraints:
 * - n == citations.length
 * - 1 <= n <= 5000
 * - 0 <= citations[i] <= 1000
 *
 * Topics: Array, Sorting, Counting Sort
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     private String[] lt20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
 *         "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
 *         "Seventeen", "Eighteen", "Nineteen"};
 *     private String[] tens
 *         = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
 *     private String[] thousands = {"Billion", "Million", "Thousand", ""};
 *     public String numberToWords(int num) {
 *         if (num == 0) {
 *             return "Zero";
 *         }
 *         StringBuilder sb = new StringBuilder();
 *         for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
 *             if (num / i== 0) {
 *                 continue;
 *             }
 *             sb.append(transfer(num / i)).append(thousands[j]).append(' ');
 *             num %= i;
 *         }
 *         return sb.toString().trim();
 *     }
 * 
 *     private String transfer(int num) {
 *         if (num == 0) {
 *             return "";
 *         }
 *         if (num < 20) {
 *             return lt20[num] + " ";
 *         }
 *         if (num < 100) {
 *             return tens[num / 10] + " " + transfer(num % 10);
 *         }
 *         return lt20[num/100] +" Hundred " + transfer(num % 100);
 *     }
 * }
 */

package hindex

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
