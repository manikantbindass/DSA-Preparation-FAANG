/*
 * LeetCode Problem 316: Remove Duplicate Letters
 * Problem Number: 316
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Given a string s, remove duplicate letters so that every letter appears once and
 * only once. You must make sure your result is the smallest in lexicographical
 * order among all possible results.
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
 * 	1 <= s.length <= 104
 * 	s consists of lowercase English letters.
 *
 *
 *
 * Note: This question is the same as 1081:
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
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
 * - 1 <= s.length <= 104
 * - s consists of lowercase English letters.
 *
 * Topics: String, Stack, Greedy, Monotonic Stack
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] last =new int[26];
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stk= new ArrayDeque<>();
        int mask = 0;
        for (int i= 0; i < n; ++i) {
            char c = s.charAt(i);
            if (((mask >> (c- 'a')) & 1) == 1) {
                continue;
            }
            while (!stk.isEmpty() && stk.peek() > c && last[stk.peek() - 'a'] > i) {
                mask ^=1 << (stk.pop() - 'a');
            }
            stk.push(c);
            mask |= 1 << (c - 'a');
        }
        StringBuilder ans=new StringBuilder();
        for (char c :stk) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }
}
