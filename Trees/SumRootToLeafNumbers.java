/*
 * LeetCode Problem 129: Sum Root to Leaf Numbers
 * Problem Number: 129
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * 
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * Return the total sum of all root-to-leaf numbers.
 * 
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Sum = 12 + 13 = 25.
 * 
 * Example 2:
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Sum = 495 + 491 + 40 = 1026.
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - 0 <= Node.val <= 9
 * - The depth of the tree will not exceed 10.
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
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }
        
        // Update current sum by appending current node's value
        currentSum = currentSum * 10 + node.val;
        
        // If it's a leaf node, return the current sum
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Otherwise, recursively process left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
