# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · divisible-game
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/divisible-game/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is to determine if we can make string t a subsequence of
#   string s by possibly removing at most one character from s. The
#   approach uses prefix and suffix arrays to track the earliest positions
#   in t that can be matched by prefixes of s, and the latest positions in
#   t that can be matched by suffixes of s. Then we check if there exists
#   an index i in s such that the prefix up to i-1 and suffix from i+1
#   together cover all characters of t, meaning removing s[i] (or not
#   removing any) allows t to be a subsequence.
# 
# Complexity
#   Time  : O(m + n)
#   Space : O(m)
# 
# Runtime  : 0 ms
# Memory   : 42.6 MB
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        m, n = len(s), len(t)
        pre = [-1] * m
        suf = [-1] * m
        p = 0
        for i in range(m):
            while p < n and t[p] != s[i]:
                p += 1
            if p == n:
                break
            pre[i] = p
            p += 1
        if m == 0 or pre[-1] != -1:
            return True
        p = n - 1
        for i in range(m - 1, -1, -1):
            while p >= 0 and t[p] != s[i]:
                p -= 1
            if p < 0:
                break
            suf[i] = p
            p -= 1
        for i in range(m):
            l = -1 if i == 0 else pre[i - 1]
            r = n if i == m - 1 else suf[i + 1]
            if i > 0 and l == -1:
                continue
            if i < m - 1 and r == -1:
                continue
            if l + 1 < r:
                return True
        return False
