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

func canMakeSubsequence(s string, t string) bool {
    m, n := len(s), len(t)
    pre := make([]int, m)
    suf := make([]int, m)
    for i := 0; i < m; i++ {
        pre[i] = -1
        suf[i] = -1
    }
    p := 0
    for i := 0; i < m; i++ {
        for p < n && t[p] != s[i] {
            p++
        }
        if p == n {
            break
        }
        pre[i] = p
        p++
    }
    if m == 0 || pre[m-1] != -1 {
        return true
    }
    p = n - 1
    for i := m - 1; i >= 0; i-- {
        for p >= 0 && t[p] != s[i] {
            p--
        }
        if p < 0 {
            break
        }
        suf[i] = p
        p--
    }
    for i := 0; i < m; i++ {
        l := -1
        if i > 0 {
            l = pre[i-1]
        }
        r := n
        if i < m-1 {
            r = suf[i+1]
        }
        if i > 0 && l == -1 {
            continue
        }
        if i < m-1 && r == -1 {
            continue
        }
        if l+1 < r {
            return true
        }
    }
    return false
}
