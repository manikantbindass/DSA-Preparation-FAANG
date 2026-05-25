/*
LeetCode Problem 99: Recover Binary Search Tree
Problem Number: 99
Difficulty: Medium
Link: https://leetcode.com/problems/recover-binary-search-tree/

You are given the root of a binary search tree (BST), where exactly two nodes 
of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: Swapping 1 and 3 recovers the BST.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: Swapping 2 and 3 recovers the BST.

Constraints:
- The number of nodes in the tree is in the range [2, 1000].
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

func recoverTree(root *TreeNode) {
    var prev, first, second *TreeNode
    
    var inorder func(node *TreeNode)
    inorder = func(node *TreeNode) {
        if node == nil {
            return
        }
        
        // Traverse left subtree
        inorder(node.Left)
        
        // Check if current node violates BST property with previous node
        if prev != nil && prev.Val > node.Val {
            if first == nil {
                first = prev
            }
            second = node
        }
        prev = node
        
        // Traverse right subtree
        inorder(node.Right)
    }
    
    inorder(root)
    
    // Swap values to recover the BST
    if first != nil && second != nil {
        first.Val, second.Val = second.Val, first.Val
    }
}
