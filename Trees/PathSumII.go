/*
LeetCode Problem 113: Path Sum II
Problem Number: 113
Difficulty: Medium
Link: https://leetcode.com/problems/path-sum-ii/

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
where the sum of the node values in the path equals targetSum.

A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:
Input: root = [1,2], targetSum = 0
Output: []

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -1000 <= Node.val <= 1000
- -1000 <= targetSum <= 1000

Topics: Tree, Depth-First Search, Backtracking, Binary Tree
Time Complexity: O(n^2) in worst case (copying lists), O(n) for traversal
Space Complexity: O(h) - where h is the height of the tree (recursion stack)
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func pathSum(root *TreeNode, targetSum int) [][]int {
    result := make([][]int, 0)
    currentPath := make([]int, 0)
    
    var dfs func(node *TreeNode, remainingSum int)
    dfs = func(node *TreeNode, remainingSum int) {
        if node == nil {
            return
        }
        
        // Add current node to path
        currentPath = append(currentPath, node.Val)
        remainingSum -= node.Val
        
        // Check if it's a leaf node and remaining sum is zero
        if node.Left == nil && node.Right == nil && remainingSum == 0 {
            // Create a copy of the current path
            pathCopy := make([]int, len(currentPath))
            copy(pathCopy, currentPath)
            result = append(result, pathCopy)
        }
        
        // Recursively traverse left and right subtrees
        dfs(node.Left, remainingSum)
        dfs(node.Right, remainingSum)
        
        // Backtrack: remove current node from path
        currentPath = currentPath[:len(currentPath)-1]
    }
    
    dfs(root, targetSum)
    return result
}
