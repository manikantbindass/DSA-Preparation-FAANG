/*
 * LeetCode Problem 2911: Minimum Changes to Make K Semi-palindromes
 * Problem Number: 2911
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
 *
 * Given a string s and an integer k, partition s into k substrings such that the
 * letter changes needed to make each substring a semi-palindrome are minimized.
 *
 * Return the minimum number of letter changes required.
 *
 * A semi-palindrome is a special type of string that can be divided into
 * palindromes based on a repeating pattern. To check if a string is a
 * semi-palindrome:​
 *
 * 	Choose a positive divisor d of the string's length. d can range from 1 up to,
 * but not including, the string's length. For a string of length 1, it does not
 * have a valid divisor as per this definition, since the only divisor is its
 * length, which is not allowed.
 * 	For a given divisor d, divide the string into groups where each group contains
 * characters from the string that follow a repeating pattern of length d.
 * Specifically, the first group consists of characters at positions 1, 1 + d, 1 +
 * 2d, and so on; the second group includes characters at positions 2, 2 + d, 2 +
 * 2d, etc.
 * 	The string is considered a semi-palindrome if each of these groups forms a
 * palindrome.
 *
 * Consider the string "abcabc":
 *
 * 	The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
 * 	For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
 * 	For d = 2:
 *
 * 		Group 1 (positions 1, 3, 5): "acb"
 * 		Group 2 (positions 2, 4, 6): "bac"
 * 		Neither group forms a palindrome.
 *
 *
 * 	For d = 3:
 *
 * 		Group 1 (positions 1, 4): "aa"
 * 		Group 2 (positions 2, 5): "bb"
 * 		Group 3 (positions 3, 6): "cc"
 * 		All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.
 *
 *
 *
 *
 *
 * Example 1: 
 *
 * Input:   s = "abcac", k = 2 
 *
 * Output:   1 
 *
 * Explanation:  Divide s into "ab" and "cac". "cac" is already semi-palindrome.
 * Change "ab" to "aa", it becomes semi-palindrome with d = 1.
 *
 * Example 2: 
 *
 * Input:   s = "abcdef", k = 2 
 *
 * Output:   2 
 *
 * Explanation:  Divide s into substrings "abc" and "def". Each needs one change to
 * become semi-palindrome.
 *
 * Example 3: 
 *
 * Input:   s = "aabbaa", k = 3 
 *
 * Output:   0 
 *
 * Explanation:  Divide s into substrings "aa", "bb" and "aa". All are already
 * semi-palindromes.
 *
 *
 *
 * Constraints:
 *
 * 	2 <= s.length <= 200
 * 	1 <= k <= s.length / 2
 * 	s contains only lowercase English letters.
 *
 * Example 1:
 * Input: "abcac"
 * Output: 2
 *
 * Constraints:
 * - 2 <= s.length <= 200
 * - 1 <= k <= s.length / 2
 * - s contains only lowercase English letters.
 *
 * Topics: Two Pointers, String, Dynamic Programming
 * Time Complexity: O(n^3 log n + k*n^2)
 * Space Complexity: O(n^2)
 * Approach: Precompute the mismatch cost for each substring over its proper divisors, then partition with dynamic programming.
 */

import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] g = new int[n + 1][n + 1];
        int[][] f = new int[n + 1][k + 1];
        final int inf = 1 << 30;
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(g[i], inf);
            Arrays.fill(f[i], inf);
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                int m = j - i + 1;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int l = 0; l < m; ++l) {
                            int r = (m / d - 1 - l / d) * d + l % d;
                            if (l >= r) {
                                break;
                            }
                            if (s.charAt(i - 1 + l) != s.charAt(i - 1 + r)) {
                                ++cnt;
                            }
                        }
                        g[i][j] = Math.min(g[i][j], cnt);
                    }
                }
            }
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i - 1; ++h) {
                    f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h + 1][i]);
                }
            }
        }
        return f[n][k];
    }
}
