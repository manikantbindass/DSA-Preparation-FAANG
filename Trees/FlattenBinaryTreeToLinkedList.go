/*
LeetCode Problem 114: Flatten Binary Tree to Linked List
Problem Number: 114
Difficulty: Medium
Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given the root of a binary tree, flatten the tree into a "linked list" in-place.
The "linked list" should use the same TreeNode class where the right child pointer 
points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]

Constraints:
- The number of nodes in the tree is in the range [0, 2000].
- -100 <= Node.val <= 100

Topics: Linked List, Stack, Tree, Depth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(1) - in-place transformation
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func flatten(root *TreeNode) {
    current := root
    
    for current != nil {
        if current.Left != nil {
            // Find the rightmost node in the left subtree
            predecessor := current.Left
            for predecessor.Right != nil {
                predecessor = predecessor.Right
            }
            
            // Connect the right subtree to the rightmost node of left subtree
            predecessor.Right = current.Right
            
            // Move left subtree to right
            current.Right = current.Left
            current.Left = nil
        }
        // Move to the next node
        current = current.Right
    }
}
