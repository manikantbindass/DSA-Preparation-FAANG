/*
 * LeetCode Problem 101: Symmetric Tree
 * Problem Number: 101
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/symmetric-tree/
 * 
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * 
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * 
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - -100 <= Node.val <= 100
 * 
 * Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - where h is the height of the tree for recursion stack
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
    public boolean isSymmetric(TreeNode root) {
        // An empty tree or single node is symmetric
        if (root == null) {
            return true;
        }
        // Check if left and right subtrees are mirrors of each other
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        // Both are null -> symmetric at this point
        if (left == null && right == null) {
            return true;
        }
        // One is null or values differ -> not symmetric
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        // Check: left's left with right's right AND left's right with right's left
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
