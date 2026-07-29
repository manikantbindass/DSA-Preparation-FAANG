/*
 * LeetCode Problem 3813: Smallest Palindromic Rearrangement II
 * Problem Number: 3813
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/
 *
 * You are given a palindromic string s and an integer k.
 *
 * Return the k-th lexicographically smallest palindromic permutation of s. If
 * there are fewer than k distinct palindromic permutations, return an empty
 * string.
 *
 * Note: Different rearrangements that yield the same palindromic string are
 * considered identical and are counted once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abba", k = 2
 *
 * Output: "baab"
 *
 * Explanation:
 *
 * 	The two distinct palindromic rearrangements of "abba" are "abba" and "baab".
 * 	Lexicographically, "abba" comes before "baab". Since k = 2, the output is
 * "baab".
 *
 * Example 2:
 *
 * Input: s = "aa", k = 2
 *
 * Output: ""
 *
 * Explanation:
 *
 * 	There is only one palindromic rearrangement: "aa".
 * 	The output is an empty string since k = 2 exceeds the number of possible
 * rearrangements.
 *
 * Example 3:
 *
 * Input: s = "bacab", k = 1
 *
 * Output: "abcba"
 *
 * Explanation:
 *
 * 	The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab".
 * 	Lexicographically, "abcba" comes before "bacab". Since k = 1, the output is
 * "abcba".
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 104
 * 	s consists of lowercase English letters.
 * 	s is guaranteed to be palindromic.
 * 	1 <= k <= 106
 *
 * Example 1:
 * Input: s = "abba", k = 2
 * Output: "baab"
 *
 * Example 2:
 * Input: s = "aa", k = 2
 * Output: ""
 *
 * Example 3:
 * Input: s = "bacab", k = 1
 * Output: "abcba"
 *
 * Constraints:
 * - 1 <= s.length <= 104
 * - s consists of lowercase English letters.
 * - s is guaranteed to be palindromic.
 * - 1 <= k <= 106
 *
 * Topics: Hash Table, Math, String, Combinatorics, Counting
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    static final long CAP =2_000_001L;
    public String smallestPalindrome(String s, int k){
        int n =s.length();
        int[] cnt =new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;
        int mid =-1;
        int[] half =new int[26];
        for (int i= 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) mid = i;
            half[i] = cnt[i] / 2;
        }
        int m = n /2;
        long total =permCount(half, m);
        if (total < k) return "";
        long rem = k;
        int left = m;
        char[] res = new char[m];
        for (int pos = 0; pos< m; pos++) {
            for (int c = 0; c< 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                left--;
                long got=permCount(half, left);
                if (got >= rem) {
                    res[pos]= (char) ('a' + c);
                    break;
                }
                rem -=got;
                half[c]++;
                left++;
            }
        }
        StringBuilder sb = new StringBuilder(n);
        sb.append(res);
        if (mid != -1) sb.append((char) ('a' + mid));
        for (int i = m- 1; i >= 0; i--) sb.append(res[i]);
        return sb.toString();
    }
    private long permCount(int[] half, int total) {
        long perm =1;
        int remain= total;
        for (int c= 0; c < 26 && perm <= CAP; c++) {
            int cc =half[c];
            if (cc == 0) continue;
            long cb= comb(remain, cc);
            perm *= cb;
            if (perm > CAP) return CAP + 1;
            remain -= cc;
        }
        return perm;
    }
    private long comb(int n, int k) {
        if (k > n - k) k= n - k;
        long res = 1;
        for (int i= 1; i <= k; i++) {
            res = res * (n - k + i) / i;
            if (res >CAP) return CAP + 1;
        }
        return res;
    }
}
