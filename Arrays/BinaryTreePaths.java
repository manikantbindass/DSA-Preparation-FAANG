/*
 * LeetCode Problem 257: Binary Tree Paths
 * Problem Number: 257
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/binary-tree-paths/
 *
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 *
 * Example 2:
 *
 * Input: root = [1]
 * Output: ["1"]
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the tree is in the range [1, 100].
 * 	-100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 *
 * Example 2:
 * Input: root = [1]
 * Output: ["1"]
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 100].
 * - -100 <= Node.val <= 100
 *
 * Topics: String, Backtracking, Tree, Depth-First Search, Binary Tree
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 42.9 MB
 */

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[t.charAt(i) - 'a'];
        }
        for (int i = 0;i < 26; ++i) {
            if (cnt[i] !=0) {
                return false;
            }
        }
        return true;
    }
}
