// ──────────────────────────────────────────────────────────────────────
// LeetCode #205 · Isomorphic Strings
// Difficulty : Easy
// Topics     : Hash Table, String
// URL        : https://leetcode.com/problems/isomorphic-strings/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use two hash maps (or arrays for ASCII) to track the mapping from
//   characters in s to characters in t and vice versa. Iterate through
//   both strings simultaneously. For each pair of characters (c1 from s,
//   c2 from t), check if there is an existing mapping. If not, create it;
//   if yes, verify it matches. Also ensure no two different characters in
//   s map to the same character in t by checking the reverse mapping. If
//   all pairs satisfy the bijection, return true; otherwise false.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1) because the maps have at most 256 entries for ASCII
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : s = "egg", t = "add"
//     Output : true
//   Example 2:
//     Input  : s = "f11", t = "b23"
//     Output : false
//   Example 3:
//     Input  : s = "paper", t = "title"
//     Output : true
// 
// Constraints
//   · 1 <= s.length <= 5 * 104
//   · t.length == s.length
//   · s and t consist of any valid ascii character.
// ──────────────────────────────────────────────────────────────────────

func isIsomorphic(s string, t string) bool {
    if len(s) != len(t) {
        return false
    }
    mapS := make([]byte, 256)
    mapT := make([]byte, 256)
    for i := 0; i < len(s); i++ {
        c1 := s[i]
        c2 := t[i]
        if mapS[c1] == 0 && mapT[c2] == 0 {
            mapS[c1] = c2
            mapT[c2] = c1
        } else if mapS[c1] != c2 || mapT[c2] != c1 {
            return false
        }
    }
    return true
}
