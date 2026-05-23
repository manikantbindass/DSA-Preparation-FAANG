/*
LeetCode Problem 94: Binary Tree Inorder Traversal
Problem Number: 94
Difficulty: Easy
Link: https://leetcode.com/problems/binary-tree-inorder-traversal/

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]
Explanation: The tree structure is:
    1
     \
      2
     /
    3

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

Topics: Stack, Tree, Depth-First Search, Binary Tree
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

func inorderTraversal(root *TreeNode) []int {
    result := make([]int, 0)
    
    var dfs func(node *TreeNode)
    dfs = func(node *TreeNode) {
        if node == nil {
            return
        }
        dfs(node.Left)
        result = append(result, node.Val)
        dfs(node.Right)
    }
    
    dfs(root)
    return result
}
