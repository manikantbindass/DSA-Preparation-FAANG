/*
 * LeetCode Problem 124: Binary Tree Maximum Path Sum
 * Problem Number: 124
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes has an edge connecting them.
 * A node can only appear in the sequence at most once. The path does not need to pass through the root.
 * 
 * The path sum of a path is the sum of the node's values along the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with sum 6.
 * 
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with sum 42.
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3 * 10^4].
 * - -1000 <= Node.val <= 1000
 * 
 * Topics: Tree, Depth-First Search, Dynamic Programming, Binary Tree
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
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Get maximum sum from left and right subtrees (only if positive)
        int leftSum = Math.max(0, dfs(node.left));
        int rightSum = Math.max(0, dfs(node.right));
        
        // Update global maximum with path passing through current node
        maxSum = Math.max(maxSum, node.val + leftSum + rightSum);
        
        // Return maximum sum of path starting from current node going down to children
        return node.val + Math.max(leftSum, rightSum);
    }
}
