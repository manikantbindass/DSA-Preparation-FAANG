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

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String t = s.substring(i, i + 10);
            if (cnt.merge(t, 1, Integer::sum) == 2) {
                ans.add(t);
            }
        }
        return ans;
    }
}
