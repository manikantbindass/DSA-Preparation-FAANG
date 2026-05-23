/*
 * LeetCode Problem 95: Unique Binary Search Trees II
 * Problem Number: 95
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/unique-binary-search-trees-ii/
 * 
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * 
 * Example 1:
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 * 
 * Constraints:
 * - 1 <= n <= 8
 * 
 * Topics: Dynamic Programming, Backtracking, Tree, Binary Search Tree, Binary Tree
 * Time Complexity: O(C_n * n) where C_n is the nth Catalan number
 * Space Complexity: O(C_n * n) - for storing all trees
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTrees(1, n);
    }
    
    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        
        // Base case: empty subtree
        if (start > end) {
            result.add(null);
            return result;
        }
        
        // Try each value as the root
        for (int rootVal = start; rootVal <= end; rootVal++) {
            // Generate all left subtrees
            List<TreeNode> leftTrees = buildTrees(start, rootVal - 1);
            // Generate all right subtrees
            List<TreeNode> rightTrees = buildTrees(rootVal + 1, end);
            
            // Combine each left and right subtree with the root
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(rootVal, left, right);
                    result.add(root);
                }
            }
        }
        
        return result;
    }
}
