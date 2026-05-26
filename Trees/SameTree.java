/*
 * LeetCode Problem 100: Same Tree
 * Problem Number: 100
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/same-tree/
 * 
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * 
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * 
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * 
 * Constraints:
 * - The number of nodes in both trees is in the range [0, 100].
 * - -10^4 <= Node.val <= 10^4
 * 
 * Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
 * Time Complexity: O(min(n, m)) - where n and m are the number of nodes in the trees
 * Space Complexity: O(min(h1, h2)) - for the recursion stack
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both are null, they are the same
        if (p == q) return true;
        // If one is null or values differ, they are not the same
        if (p == null || q == null || p.val != q.val) return false;
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
