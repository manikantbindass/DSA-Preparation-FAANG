// ──────────────────────────────────────────────────────────────────────
// LeetCode #1460 · Number of Substrings Containing All Three Characters
// Difficulty : Medium
// Topics     : Hash Table, String, Sliding Window
// URL        : https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a sliding window approach with three pointers tracking the last
//   occurrence of each character 'a', 'b', and 'c'. As we iterate through
//   the string, we update the last seen index for the current character.
//   For each position i, the number of valid substrings ending at i that
//   contain all three characters is equal to (min(lastA, lastB, lastC) +
//   1). This works because any substring starting at or before the
//   earliest of the three last occurrences will include all three
//   characters. We accumulate this count for each i to get the total
//   number of substrings.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : s = "abcabc"
//     Output : 10
//     Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
//   Example 2:
//     Input  : s = "aaacb"
//     Output : 3
//     Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
//   Example 3:
//     Input  : s = "abc"
//     Output : 1
// 
// Constraints
//   · 3 <= s.length <= 5 x 10^4
//   · s only consists of a, b or c characters.
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public int numberOfSubstrings(String s) {
        int[] last = new int[] {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            last[c - 'a'] = i;
            ans += Math.min(last[0], Math.min(last[1], last[2])) + 1;
        }
        return ans;
    }
}
