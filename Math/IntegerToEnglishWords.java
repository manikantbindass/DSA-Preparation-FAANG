/*
 * LeetCode Problem 273: Integer to English Words
 * Problem Number: 273
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/integer-to-english-words/
 *
 * Convert a non-negative integer num to its English words representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 *
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 *
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 *
 *
 * Constraints:
 *
 * 	0 <= num <= 231 - 1
 *
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Constraints:
 * - 0 <= num <= 231 - 1
 *
 * Topics: Math, String, Recursion
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    private String[] lt20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens
        = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"Billion", "Million", "Thousand", ""};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            if (num / i== 0) {
                continue;
            }
            sb.append(transfer(num / i)).append(thousands[j]).append(' ');
            num %= i;
        }
        return sb.toString().trim();
    }

    private String transfer(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lt20[num] + " ";
        }
        if (num < 100) {
            return tens[num / 10] + " " + transfer(num % 10);
        }
        return lt20[num/100] +" Hundred " + transfer(num % 100);
    }
}
