/*
 * LeetCode Problem 117: Populating Next Right Pointers in Each Node II
 * Problem Number: 117
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * 
 * Given a binary tree, populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * 
 * Example 2:
 * Input: root = []
 * Output: []
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 6000].
 * - -100 <= Node.val <= 100
 * 
 * Topics: Linked List, Tree, Depth-First Search, Breadth-First Search, Binary Tree
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(w) - where w is the maximum width of the tree (queue size)
 */

import java.util.ArrayDeque;
import java.util.Deque;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;
            
            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();
                
                // Connect previous node to current node
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
                
                // Add children to queue
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        
        return root;
    }
}
