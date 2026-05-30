/*
 * LeetCode Problem 111: Minimum Depth of Binary Tree
 * Problem Number: 111
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * 
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * 
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^5].
 * - -1000 <= Node.val <= 1000
 * 
 * Topics: Tree, Breadth-First Search, Depth-First Search, Binary Tree
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(w) - where w is the maximum width of the tree
 */

import java.util.ArrayDeque;
import java.util.Deque;

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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            depth++;
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Check if this is a leaf node
                if (node.left == null && node.right == null) {
                    return depth;
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return depth;
    }
}
