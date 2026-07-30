/*
 * LeetCode Problem 290: Word Pattern
 * Problem Number: 290
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/word-pattern/
 *
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter
 * in pattern and a non-empty word in s. Specifically:
 *
 * 	Each letter in pattern maps to exactly one unique word in s.
 * 	Each unique word in s maps to exactly one letter in pattern.
 * 	No two letters map to the same word, and no two words map to the same letter.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 *
 * Output: true
 *
 * Explanation:
 *
 * The bijection can be established as:
 *
 * 	'a' maps to "dog".
 * 	'b' maps to "cat".
 *
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 *
 * Output: false
 *
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 *
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 	1 <= pattern.length <= 300
 * 	pattern contains only lower-case English letters.
 * 	1 <= s.length <= 3000
 * 	s contains only lowercase English letters and spaces ' '.
 * 	s does not contain any leading or trailing spaces.
 * 	All the words in s are separated by a single space.
 *
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 *
 * Constraints:
 * - 1 <= pattern.length <= 300
 * - pattern contains only lower-case English letters.
 * - 1 <= s.length <= 3000
 * - s contains only lowercase English letters and spaces ' '.
 * - s
 *
 * Topics: Hash Table, String
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * Runtime: 0 ms
 * Memory: 42.6 MB
 */

class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m;++i) {
            for (int j= 0; j < n; ++j) {
                int live =-board[i][j];
                for (int x =i- 1; x <= i +1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < m && y>=0 && y < n && board[x][y] > 0) {
                            ++live;
                        }
                    }
                }
                if (board[i][j]==1 && (live<2 || live > 3)) {
                    board[i][j] =2;
                }
                if (board[i][j]==0 && live== 3) {
                    board[i][j]= -1;
                }
            }
        }
        for (int i =0; i<m; ++i) {
            for (int j= 0; j< n;++j) {
                if (board[i][j]== 2) {
                    board[i][j]= 0;
                } else if (board[i][j]== -1) {
                    board[i][j]=1;
                }
            }
        }
    }
}
