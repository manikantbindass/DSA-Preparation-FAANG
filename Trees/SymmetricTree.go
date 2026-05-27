/*
LeetCode Problem 101: Symmetric Tree
Problem Number: 101
Difficulty: Easy
Link: https://leetcode.com/problems/symmetric-tree/

Given the root of a binary tree, check whether it is a mirror of itself
(i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
- The number of nodes in the tree is in the range [1, 1000].
- -100 <= Node.val <= 100

Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - where h is the height of the tree for recursion stack
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func isSymmetric(root *TreeNode) bool {
    // An empty tree or single node is symmetric
    if root == nil {
        return true
    }
    // Check if left and right subtrees are mirrors of each other
    return isMirror(root.Left, root.Right)
}

func isMirror(left, right *TreeNode) bool {
    // Both are nil -> symmetric at this point
    if left == nil && right == nil {
        return true
    }
    // One is nil or values differ -> not symmetric
    if left == nil || right == nil || left.Val != right.Val {
        return false
    }
    // Check: left's left with right's right AND left's right with right's left
    return isMirror(left.Left, right.Right) && isMirror(left.Right, right.Left)
}
