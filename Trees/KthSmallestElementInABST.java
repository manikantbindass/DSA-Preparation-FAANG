/*
 * LeetCode Problem 230: Kth Smallest Element in a BST
 * Problem Number: 230
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Given the root of a binary search tree, and an integer k, return the kth
 * smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the tree is n.
 * 	1 <= k <= n <= 104
 * 	0 <= Node.val <= 104
 *
 *
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete
 * operations) and you need to find the kth smallest frequently, how would you
 * optimize?
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 104
 * - 0 <= Node.val <= 104
 *
 * Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree
 * Time Complexity: O(log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42 MB
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
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stk =new ArrayDeque<>();
        while (root != null || !stk.isEmpty()) {
            if (root !=null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                if (--k== 0) {
                    return root.val;
                }
                root =root.right;
            }
        }
        return 0;
    }
}
