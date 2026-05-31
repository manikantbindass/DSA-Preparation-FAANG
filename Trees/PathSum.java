/*
 * LeetCode Problem 112: Path Sum
 * Problem Number: 112
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/path-sum/
 * 
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a 
 * root-to-leaf path such that adding up all the values along the path equals targetSum.
 * 
 * A leaf is a node with no children.
 * 
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The path 5 -> 4 -> 11 -> 2 sums to 22.
 * 
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * 
 * Example 3:
 * Input: root = [], targetSum = 0
 * Output: false
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 * 
 * Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }
    
    private boolean dfs(TreeNode node, int remainingSum) {
        if (node == null) {
            return false;
        }
        
        // Subtract current node's value from remaining sum
        remainingSum -= node.val;
        
        // Check if it's a leaf node and remaining sum is zero
        if (node.left == null && node.right == null) {
            return remainingSum == 0;
        }
        
        // Recursively check left and right subtrees
        return dfs(node.left, remainingSum) || dfs(node.right, remainingSum);
    }
}
