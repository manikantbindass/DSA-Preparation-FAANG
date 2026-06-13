/*
 * LeetCode Problem 145: Binary Tree Postorder Traversal
 * Problem Number: 145
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * 
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * 
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
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
 * Space Complexity: O(n) - for the stack
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // Add to front of list (reverse order of preorder)
            result.addFirst(node.val);
            
            // Push left first, then right (so right is processed first due to LIFO)
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return result;
    }
}
