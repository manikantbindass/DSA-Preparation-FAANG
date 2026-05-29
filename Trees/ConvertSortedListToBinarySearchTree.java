/*
 * LeetCode Problem 109: Convert Sorted List to Binary Search Tree
 * Problem Number: 109
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * 
 * Given the head of a singly linked list where elements are sorted in ascending order,
 * convert it into a height-balanced binary search tree.
 * 
 * Example 1:
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5] which represents the height-balanced BST.
 * 
 * Example 2:
 * Input: head = []
 * Output: []
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 2 * 10^4].
 * - -10^5 <= Node.val <= 10^5
 * 
 * Topics: Linked List, Divide and Conquer, Tree, Binary Search Tree, Binary Tree
 * Time Complexity: O(n) - building the list and constructing tree
 * Space Complexity: O(n) - for the list conversion and recursion stack
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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
    private List<Integer> values = new ArrayList<>();
    
    public TreeNode sortedListToBST(ListNode head) {
        // Convert linked list to array list for O(1) access
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        // Build BST recursively
        return buildBST(0, values.size() - 1);
    }
    
    private TreeNode buildBST(int left, int right) {
        if (left > right) {
            return null;
        }
        // Choose middle element as root to maintain balance
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = buildBST(left, mid - 1);
        root.right = buildBST(mid + 1, right);
        return root;
    }
}
