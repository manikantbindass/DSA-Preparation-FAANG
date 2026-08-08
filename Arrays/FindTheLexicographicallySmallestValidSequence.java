/*
 * LeetCode Problem 3584: Find the Lexicographically Smallest Valid Sequence
 * Problem Number: 3584
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/
 *
 * You are given two strings word1 and word2.
 *
 * A string x is called almost equal to y if you can change at most one character
 * in x to make it identical to y.
 *
 * A sequence of indices seq is called valid if:
 *
 * 	The indices are sorted in ascending order.
 * 	Concatenating the characters at these indices in word1 in the same order
 * results in a string that is almost equal to word2.
 *
 * Return an array of size word2.length representing the lexicographically smallest
 * valid sequence of indices. If no such sequence of indices exists, return an
 * empty array.
 *
 * Note that the answer must represent the lexicographically smallest array, not
 * the corresponding string formed by those indices.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "vbcca", word2 = "abc"
 *
 * Output: [0,1,2]
 *
 * Explanation:
 *
 * The lexicographically smallest valid sequence of indices is [0, 1, 2]:
 *
 * 	Change word1[0] to 'a'.
 * 	word1[1] is already 'b'.
 * 	word1[2] is already 'c'.
 *
 * Example 2:
 *
 * Input: word1 = "bacdc", word2 = "abc"
 *
 * Output: [1,2,4]
 *
 * Explanation:
 *
 * The lexicographically smallest valid sequence of indices is [1, 2, 4]:
 *
 * 	word1[1] is already 'a'.
 * 	Change word1[2] to 'b'.
 * 	word1[4] is already 'c'.
 *
 * Example 3:
 *
 * Input: word1 = "aaaaaa", word2 = "aaabc"
 *
 * Output: []
 *
 * Explanation:
 *
 * There is no valid sequence of indices.
 *
 * Example 4:
 *
 * Input: word1 = "abc", word2 = "ab"
 *
 * Output: [0,1]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= word2.length < word1.length <= 3 * 105
 * 	word1 and word2 consist only of lowercase English letters.
 *
 * Example 1:
 * Input: word1 = "vbcca", word2 = "abc"
 * Output: [0,1,2]
 *
 * Example 2:
 * Input: word1 = "bacdc", word2 = "abc"
 * Output: [1,2,4]
 *
 * Example 3:
 * Input: word1 = "aaaaaa", word2 = "aaabc"
 * Output: []
 *
 * Example 4:
 * Input: word1 = "abc", word2 = "ab"
 * Output: [0,1]
 *
 * Constraints:
 * - 1 <= word2.length < word1.length <= 3 * 105
 * - word1 and word2 consist only of lowercase English letters.
 *
 * Topics: Two Pointers, String, Dynamic Programming, Greedy
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] suf = new int[m + 1];
        suf[m] = n;
        int j = n - 1;
        for (int i = m-1;i>=0;i--) {
            if (j >= 0 && word1.charAt(i)==word2.charAt(j)) {
                j--;
            }
            suf[i]=j+1;
        }
        int[] ans=new int[n];
        int size=0;
        boolean changed=false;
        j = 0;
        for (int i=0;i<m;i++) {
            char c = word1.charAt(i);
            if (c == word2.charAt(j) || (!changed && suf[i+1]<=j+1)) {
                if (c !=word2.charAt(j)) {
                    changed=true;
                }
                ans[size++]=i;
                j++;
                if (j ==n) {
                    return ans;
                }
            }
        }
        return new int[0];
    }
}
