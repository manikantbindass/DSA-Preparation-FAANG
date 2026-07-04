// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · count-distinct-ways-to-form-target-from-two-strings
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/count-distinct-ways-to-form-target-from-two-strings/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to transform string s1 into s2 using minimum
//   operations. Each operation can flip a '0' to '1' (cost 1) or flip a
//   '1' to '0' and optionally flip the next character (if it's '0' flip to
//   '1', else flip to '0') with cost 2. The algorithm iterates through the
//   strings, and when a mismatch is found at position i, it applies the
//   appropriate operation: if s1[i] is '0', just flip it to '1' (cost 1);
//   if s1[i] is '1', it flips it to '0' and also flips the next character
//   (if exists) to minimize future mismatches (cost 2). After the loop,
//   handle the last character separately if needed. If the last character
//   is '0' and mismatched, one flip suffices; if it's '1' and mismatched,
//   two flips are needed (unless n==1, then impossible).
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 43 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func minOperations(s1 string, s2 string) int {
    n := len(s1)
    a := []byte(s1)
    b := []byte(s2)
    ops := 0
    for i := 0; i < n-1; i++ {
        if a[i] == b[i] {
            continue
        }
        if a[i] == '0' {
            a[i] = '1'
            ops++
        } else {
            if a[i+1] == '0' {
                a[i+1] = '1'
                ops++
            }
            a[i] = '0'
            a[i+1] = '0'
            ops++
        }
    }
    if a[n-1] != b[n-1] {
        if a[n-1] == '0' {
            ops++
        } else {
            if n == 1 {
                return -1
            }
            ops += 2
        }
    }
    return ops
}
