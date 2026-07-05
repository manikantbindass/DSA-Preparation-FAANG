// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · divisible-game
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/divisible-game/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to determine if we can make string t a subsequence of
//   string s by possibly removing at most one character from s. The
//   approach uses prefix and suffix arrays to track the earliest positions
//   in t that can be matched by prefixes of s, and the latest positions in
//   t that can be matched by suffixes of s. Then we check if there exists
//   an index i in s such that the prefix up to i-1 and suffix from i+1
//   together cover all characters of t, meaning removing s[i] (or not
//   removing any) allows t to be a subsequence.
// 
// Complexity
//   Time  : O(m + n)
//   Space : O(m)
// 
// Runtime  : 0 ms
// Memory   : 42.6 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int[] pre = new int[m];
        int[] suf = new int[m];
        for (int i = 0; i < m; i++) pre[i] = -1;
        for (int i = 0; i < m; i++) suf[i] = -1;
        int p = 0;
        for (int i = 0; i < m; i++) {
            while (p < n && t.charAt(p) != s.charAt(i)) p++;
            if (p == n) break;
            pre[i] = p++;
        }
        if (m == 0 || pre[m - 1] != -1) return true;
        p = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (p >= 0 && t.charAt(p) != s.charAt(i)) p--;
            if (p < 0) break;
            suf[i] = p--;
        }
        for (int i = 0; i < m; i++) {
            int l = (i == 0) ? -1 : pre[i - 1];
            int r = (i == m - 1) ? n : suf[i + 1];
            if (i > 0 && l == -1) continue;
            if (i < m - 1 && r == -1) continue;
            if (l + 1 < r) return true;
        }
        return false;
    }
}
