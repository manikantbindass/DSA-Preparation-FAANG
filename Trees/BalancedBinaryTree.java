/*
 * LeetCode Problem 110: Balanced Binary Tree
 * Problem Number: 110
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/balanced-binary-tree/
 * 
 * Given a binary tree, determine if it is height-balanced.
 * 
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees
 * of every node never differs by more than one.
 * 
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * 
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * 
 * Example 3:
 * Input: root = []
 * Output: true
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -10^4 <= Node.val <= 10^4
 * 
 * Topics: Tree, Depth-First Search, Binary Tree
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - where h is the height of the tree (recursion stack)
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
    public boolean isBalanced(TreeNode root) {
        // If height returns -1, tree is unbalanced
        return getHeight(root) != -1;
    }
    
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Get height of left subtree
        int leftHeight = getHeight(node.left);
        // If left subtree is unbalanced, propagate -1 up
        if (leftHeight == -1) {
            return -1;
        }
        
        // Get height of right subtree
        int rightHeight = getHeight(node.right);
        // If right subtree is unbalanced, propagate -1 up
        if (rightHeight == -1) {
            return -1;
        }
        
        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        // Return height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
