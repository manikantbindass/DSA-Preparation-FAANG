// ──────────────────────────────────────────────────────────────────────
// LeetCode #187 · Repeated DNA Sequences
// Difficulty : Medium
// Topics     : Hash Table, String, Bit Manipulation, Sliding Window, Rolling Hash, Hash Function
// URL        : https://leetcode.com/problems/repeated-dna-sequences/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires finding all 10-character-long substrings that
//   occur more than once in a given DNA sequence. The approach uses a
//   sliding window of length 10 to extract each substring, and a hash map
//   to count occurrences. When a substring is seen for the second time, it
//   is added to the result list. This ensures each repeated sequence is
//   added only once. The solution is straightforward and efficient for the
//   given constraints.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 22
// Memory   : 55584000
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func findRepeatedDnaSequences(s string) []string {
    cnt := make(map[string]int)
    ans := []string{}
    for i := 0; i <= len(s)-10; i++ {
        t := s[i : i+10]
        cnt[t]++
        if cnt[t] == 2 {
            ans = append(ans, t)
        }
    }
    return ans
}
