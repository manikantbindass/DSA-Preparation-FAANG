# ──────────────────────────────────────────────────────────────────────
# LeetCode #214 · Shortest Palindrome
# Difficulty : Hard
# Topics     : String, Rolling Hash, String Matching, Hash Function
# URL        : https://leetcode.com/problems/shortest-palindrome/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem asks to find the shortest palindrome by adding characters
#   in front of the given string. The key is to find the longest
#   palindrome prefix of s. Once we have that, we take the remaining
#   suffix (the part after the palindrome prefix), reverse it, and prepend
#   it to s. To find the longest palindrome prefix efficiently, we can use
#   the KMP (Knuth-Morris-Pratt) algorithm. We create a temporary string t
#   = s + '#' + reverse(s). Then we compute the longest proper prefix of t
#   that is also a suffix (LPS array). The LPS value at the last position
#   gives the length of the longest palindrome prefix. Then we take the
#   substring from that length to the end of s, reverse it, and prepend to
#   s.
# 
# Complexity
#   Time  : O(n)
#   Space : O(n)
# 
# Runtime  : 0 ms
# Memory   : 42.4 MB
# 
# Examples
#   Example 1:
#     Input  : s = "aacecaaa"
#     Output : "aaacecaaa"
#   Example 2:
#     Input  : s = "abcd"
#     Output : "dcbabcd"
# 
# Constraints
#   · 0 <= s.length <= 5 * 104
#   · s consists of lowercase English letters only.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def shortestPalindrome(self, s: str) -> str:
        rev = s[::-1]
        t = s + '#' + rev
        lps = self.compute_lps(t)
        longest_palindrome_prefix_len = lps[-1]
        suffix = s[longest_palindrome_prefix_len:]
        return suffix[::-1] + s
    
    def compute_lps(self, pattern: str) -> list:
        n = len(pattern)
        lps = [0] * n
        length = 0
        i = 1
        while i < n:
            if pattern[i] == pattern[length]:
                length += 1
                lps[i] = length
                i += 1
            else:
                if length != 0:
                    length = lps[length - 1]
                else:
                    lps[i] = 0
                    i += 1
        return lps
