# ──────────────────────────────────────────────────────────────────────
# LeetCode #205 · Isomorphic Strings
# Difficulty : Easy
# Topics     : Hash Table, String
# URL        : https://leetcode.com/problems/isomorphic-strings/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use two hash maps (or arrays for ASCII) to track the mapping from
#   characters in s to characters in t and vice versa. Iterate through
#   both strings simultaneously. For each pair of characters (c1 from s,
#   c2 from t), check if there is an existing mapping. If not, create it;
#   if yes, verify it matches. Also ensure no two different characters in
#   s map to the same character in t by checking the reverse mapping. If
#   any inconsistency is found, return false. If the loop completes,
#   return true.
# 
# Complexity
#   Time  : O(n)
#   Space : O(1)
# 
# Runtime  : 0 ms
# Memory   : 43 MB
# 
# Examples
#   Example 1:
#     Input  : s = "egg", t = "add"
#     Output : true
#   Example 2:
#     Input  : s = "f11", t = "b23"
#     Output : false
#   Example 3:
#     Input  : s = "paper", t = "title"
#     Output : true
# 
# Constraints
#   · 1 <= s.length <= 5 * 104
#   · t.length == s.length
#   · s and t consist of any valid ascii character.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        map_s = {}
        map_t = {}
        for c1, c2 in zip(s, t):
            if c1 not in map_s and c2 not in map_t:
                map_s[c1] = c2
                map_t[c2] = c1
            elif map_s.get(c1) != c2 or map_t.get(c2) != c1:
                return False
        return True
