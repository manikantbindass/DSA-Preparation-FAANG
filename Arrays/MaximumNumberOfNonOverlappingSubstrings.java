/*
 * LeetCode Problem 1644: Maximum Number of Non-Overlapping Substrings
 * Problem Number: 1644
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/
 *
 * Given a string s of lowercase letters, you need to find the maximum number of
 * non-empty substrings of s that meet the following conditions:
 *
 * 	The substrings do not overlap, that is for any two substrings s[i..j] and
 * s[x..y], either j < x or i > y is true.
 * 	A substring that contains a certain character c must also contain all
 * occurrences of c.
 *
 * Find the maximum number of substrings that meet the above conditions. If there
 * are multiple solutions with the same number of substrings, return the one with
 * minimum total length. It can be shown that there exists a unique solution of
 * minimum total length.
 *
 * Notice that you can return the substrings in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the
 * conditions:
 * [
 * "adefaddaccc"
 * "adefadda",
 * "ef",
 * "e",
 * "f",
 * "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get only
 * 1. If we choose "adefadda", we are left with "ccc" which is the only one that
 * doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal
 * to choose "ef" since it can be split into two. Therefore, the optimal way is to
 * choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the
 * same number of substrings exist.
 *
 * Example 2:
 *
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has
 * length 3, it's considered incorrect since it has larger total length.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= s.length <= 105
 * 	s contains only lowercase English letters.
 *
 * Example 1:
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the conditions:
 *
 * Example 2:
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 *
 * Constraints:
 * - 1 <= s.length <= 105
 * - s contains only lowercase English letters.
 *
 * Topics: Hash Table, String, Greedy, Sorting
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        List<int[]> segs = new ArrayList<>();
        for (int x = 0; x < 26; ++x) {
            if (first[x]== -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i<= r; ++i) {
                int y = s.charAt(i) - 'a';
                if (first[y] < l) {
                    break;
                }
                r =Math.max(r, last[y]);
            }
            if (i>r) {
                segs.add(new int[] {l, r});
            }
        }
        segs.sort((a, b) -> a[1] - b[1]);
        List<String> ans = new ArrayList<>();
        int end = -1;
        for (int[] e : segs) {
            int l = e[0], r = e[1];
            if (l > end) {
                ans.add(s.substring(l, r + 1));
                end= r;
            }
        }
        return ans;
    }
}
