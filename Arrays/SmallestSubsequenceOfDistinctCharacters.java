/*
 * LeetCode Problem 1159: Smallest Subsequence of Distinct Characters
 * Problem Number: 1159
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * Given a string s, return the lexicographically smallest subsequence of s that
 * contains all the distinct characters of s exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 1000
 * 	s consists of lowercase English letters.
 *
 *
 *
 * Note: This question is the same as 316:
 * https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Example 1:
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consists of lowercase English letters.
 *
 * Topics: String, Stack, Greedy, Monotonic Stack
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public String smallestSubsequence(String text) {
        int[] cnt =new int[26];
        for (char c : text.toCharArray()) {
            ++cnt[c- 'a'];
        }
        boolean[] vis= new boolean[26];
        char[] cs= new char[text.length()];
        int top =-1;
        for (char c: text.toCharArray()) {
            --cnt[c - 'a'];
            if (!vis[c - 'a']) {
                while (top >= 0 && c < cs[top] && cnt[cs[top] - 'a'] > 0) {
                    vis[cs[top--] - 'a'] = false;
                }
                cs[++top] = c;
                vis[c - 'a']= true;
            }
        }
        return String.valueOf(cs,0,top + 1);
    }
}
