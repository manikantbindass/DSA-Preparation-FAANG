// ──────────────────────────────────────────────────────────────────────
// LeetCode #214 · Shortest Palindrome
// Difficulty : Hard
// Topics     : String, Rolling Hash, String Matching, Hash Function
// URL        : https://leetcode.com/problems/shortest-palindrome/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem asks to find the shortest palindrome by adding characters
//   in front of the given string. The key is to find the longest
//   palindrome prefix of s. Once we have that, we take the remaining
//   suffix (the part after the palindrome prefix), reverse it, and prepend
//   it to s. To find the longest palindrome prefix efficiently, we can use
//   the KMP (Knuth-Morris-Pratt) algorithm. We create a temporary string t
//   = s + '#' + reverse(s). Then we compute the longest proper prefix of t
//   that is also a suffix (LPS array). The LPS value at the last position
//   gives the length of the longest palindrome prefix. Then we take the
//   substring from that length to the end of s, reverse it, and prepend to
//   s.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 0 ms
// Memory   : 42.4 MB
// 
// Examples
//   Example 1:
//     Input  : s = "aacecaaa"
//     Output : "aaacecaaa"
//   Example 2:
//     Input  : s = "abcd"
//     Output : "dcbabcd"
// 
// Constraints
//   · 0 <= s.length <= 5 * 104
//   · s consists of lowercase English letters only.
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String t = s + '#' + rev;
        int[] lps = computeLPS(t);
        int len = lps[t.length() - 1];
        String suffix = s.substring(len);
        return new StringBuilder(suffix).reverse().toString() + s;
    }
    
    private int[] computeLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
