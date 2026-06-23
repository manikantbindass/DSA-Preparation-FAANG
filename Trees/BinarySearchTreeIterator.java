/*
 * LeetCode Problem 173: Binary Search Tree Iterator
 * Problem Number: 173
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/binary-search-tree-iterator/
 * 
 * Implement the BSTIterator class that represents an iterator over the in-order traversal
 * of a binary search tree (BST):
 * - BSTIterator(TreeNode root) initializes an object.
 * - int next() returns the next smallest number in the BST.
 * - boolean hasNext() returns true if there exists a next number, or false otherwise.
 * 
 * Example:
 * Input: ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 *        [[[7,3,15,null,null,9,20]], [], [], [], [], [], [], [], [], []]
 * Output: [null, 3, 7, true, 9, true, 15, true, 20, false]
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^5].
 * - 0 <= Node.val <= 10^6
 * - At most 10^5 calls will be made to next and hasNext.
 * 
 * Topics: Stack, Tree, Depth-First Search, Binary Search Tree, Binary Tree, Iterator
 * Time Complexity: O(1) for next() and hasNext() on average
 * Space Complexity: O(h) - where h is the height of the tree
 */

import java.util.ArrayDeque;
import java.util.Deque;
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
class BSTIterator {
    private Deque<TreeNode> stack = new ArrayDeque<>();
    
    public BSTIterator(TreeNode root) {
        // Push all left nodes onto stack
        pushLeft(root);
    }
    
    public int next() {
        TreeNode node = stack.pop();
        // After popping, push the left nodes of the right child
        pushLeft(node.right);
        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
