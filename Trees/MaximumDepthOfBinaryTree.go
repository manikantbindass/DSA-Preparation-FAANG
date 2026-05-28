/*
LeetCode Problem 104: Maximum Depth of Binary Tree
Problem Number: 104
Difficulty: Easy
Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/

Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:
- The number of nodes in the tree is in the range [0, 10^4].
- -100 <= Node.val <= 100

Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
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

func maxDepth(root *TreeNode) int {
    // Base case: empty tree has depth 0
    if root == nil {
        return 0
    }
    
    // Recursively compute depth of left and right subtrees
    leftDepth := maxDepth(root.Left)
    rightDepth := maxDepth(root.Right)
    
    // Depth of current node = 1 + max(depth of left, depth of right)
    if leftDepth > rightDepth {
        return 1 + leftDepth
    }
    return 1 + rightDepth
}
