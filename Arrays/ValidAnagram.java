/*
 * LeetCode Problem 242: Valid Anagram
 * Problem Number: 242
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-anagram/
 *
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 *
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length, t.length <= 5 * 104
 * 	s and t consist of lowercase English letters.
 *
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt
 * your solution to such a case?
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 104
 * - s and t consist of lowercase English letters.
 *
 * Topics: Hash Table, String, Sorting
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[t.charAt(i) - 'a'];
        }
        for (int i = 0;i < 26; ++i) {
            if (cnt[i] !=0) {
                return false;
            }
        }
        return true;
    }
}
