/*
 * LeetCode Problem 0: minimum-number-of-string-groups-through-transformations
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-number-of-string-groups-through-transformations/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 2 ms
 * Memory: 42.9 MB
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int c =0;
    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return c;
    }
    private int dfs(TreeNode r) {
        if (r== null) return Integer.MIN_VALUE;
        int l =dfs(r.left);
        int x =dfs(r.right);
        int m = Math.max(r.val, Math.max(l, x));
        if (r.val== m) c++;
        return m;
    }
}
