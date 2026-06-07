/*
LeetCode Problem 2196: Create Binary Tree From Descriptions
Problem Number: 2196
Difficulty: Medium
Link: https://leetcode.com/problems/create-binary-tree-from-descriptions/

You are given a 2D integer array descriptions where descriptions[i] = [parent_i, child_i, isLeft_i]
indicates that parent_i is the parent of child_i in a binary tree.

Return the root of the binary tree.

Example 1:
Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]

Example 2:
Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]

Constraints:
- 1 <= descriptions.length <= 10^4
- descriptions[i].length == 3
- 1 <= parent_i, child_i <= 10^5
- 0 <= isLeft_i <= 1

Topics: Array, Hash Table, Tree, Binary Tree
Time Complexity: O(n) - single pass through descriptions
Space Complexity: O(n) - for storing nodes and children set
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func createBinaryTree(descriptions [][]int) *TreeNode {
    nodeMap := make(map[int]*TreeNode)
    children := make(map[int]bool)
    
    for _, desc := range descriptions {
        parentVal := desc[0]
        childVal := desc[1]
        isLeft := desc[2]
        
        // Get or create parent node
        if _, exists := nodeMap[parentVal]; !exists {
            nodeMap[parentVal] = &TreeNode{Val: parentVal}
        }
        // Get or create child node
        if _, exists := nodeMap[childVal]; !exists {
            nodeMap[childVal] = &TreeNode{Val: childVal}
        }
        
        parent := nodeMap[parentVal]
        child := nodeMap[childVal]
        
        // Set left or right child
        if isLeft == 1 {
            parent.Left = child
        } else {
            parent.Right = child
        }
        
        // Mark child as having a parent
        children[childVal] = true
    }
    
    // Find the root (node that is never a child)
    for val, node := range nodeMap {
        if !children[val] {
            return node
        }
    }
    
    return nil
}
