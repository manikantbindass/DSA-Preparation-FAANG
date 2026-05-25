/*
LeetCode Problem 98: Validate Binary Search Tree
Problem Number: 98
Difficulty: Medium
Link: https://leetcode.com/problems/validate-binary-search-tree/

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:
- The number of nodes in the tree is in the range [1, 10^4].
- -2^31 <= Node.val <= 2^31 - 1

Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(n) - for the recursion stack (worst-case for skewed tree)
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func isValidBST(root *TreeNode) bool {
    var prev *TreeNode
    
    var inorder func(node *TreeNode) bool
    inorder = func(node *TreeNode) bool {
        if node == nil {
            return true
        }
        
        // Check left subtree
        if !inorder(node.Left) {
            return false
        }
        
        // Check current node against previous node
        if prev != nil && prev.Val >= node.Val {
            return false
        }
        prev = node
        
        // Check right subtree
        return inorder(node.Right)
    }
    
    return inorder(root)
}
