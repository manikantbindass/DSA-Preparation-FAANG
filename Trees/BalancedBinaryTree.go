/*
LeetCode Problem 110: Balanced Binary Tree
Problem Number: 110
Difficulty: Easy
Link: https://leetcode.com/problems/balanced-binary-tree/

Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees
of every node never differs by more than one.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -10^4 <= Node.val <= 10^4

Topics: Tree, Depth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - where h is the height of the tree (recursion stack)
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func isBalanced(root *TreeNode) bool {
    // If height returns -1, tree is unbalanced
    return getHeight(root) != -1
}

func getHeight(node *TreeNode) int {
    if node == nil {
        return 0
    }
    
    // Get height of left subtree
    leftHeight := getHeight(node.Left)
    // If left subtree is unbalanced, propagate -1 up
    if leftHeight == -1 {
        return -1
    }
    
    // Get height of right subtree
    rightHeight := getHeight(node.Right)
    // If right subtree is unbalanced, propagate -1 up
    if rightHeight == -1 {
        return -1
    }
    
    // Check if current node is balanced
    if leftHeight-rightHeight > 1 || rightHeight-leftHeight > 1 {
        return -1
    }
    
    // Return height of current node
    if leftHeight > rightHeight {
        return 1 + leftHeight
    }
    return 1 + rightHeight
}
