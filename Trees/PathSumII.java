/*
 * LeetCode Problem 113: Path Sum II
 * Problem Number: 113
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/path-sum-ii/
 * 
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum.
 * 
 * A leaf is a node with no children.
 * 
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * 
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * 
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 * 
 * Topics: Tree, Depth-First Search, Backtracking, Binary Tree
 * Time Complexity: O(n^2) in worst case (copying lists), O(n) for traversal
 * Space Complexity: O(h) - where h is the height of the tree (recursion stack)
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
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> currentPath = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return result;
    }
    
    private void dfs(TreeNode node, int remainingSum) {
        if (node == null) {
            return;
        }
        
        // Add current node to path
        currentPath.add(node.val);
        remainingSum -= node.val;
        
        // Check if it's a leaf node and remaining sum is zero
        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(currentPath));
        }
        
        // Recursively traverse left and right subtrees
        dfs(node.left, remainingSum);
        dfs(node.right, remainingSum);
        
        // Backtrack: remove current node from path
        currentPath.remove(currentPath.size() - 1);
    }
}
