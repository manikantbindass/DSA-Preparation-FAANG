/*
 * LeetCode Problem 144: Binary Tree Preorder Traversal
 * Problem Number: 144
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
 * 
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * 
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * 
 * Example 2:
 * Input: root = []
 * Output: []
 * 
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 * 
 * Topics: Stack, Tree, Depth-First Search, Binary Tree
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(n) - for the recursion stack (worst-case for skewed tree)
 */

import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> result = new ArrayList<>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return result;
    }
    
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // Preorder: root -> left -> right
        result.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }
}
