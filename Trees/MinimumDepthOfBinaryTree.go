/*
LeetCode Problem 111: Minimum Depth of Binary Tree
Problem Number: 111
Difficulty: Easy
Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:
- The number of nodes in the tree is in the range [0, 10^5].
- -1000 <= Node.val <= 1000

Topics: Tree, Breadth-First Search, Depth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - where w is the maximum width of the tree
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func minDepth(root *TreeNode) int {
    if root == nil {
        return 0
    }
    
    queue := []*TreeNode{root}
    depth := 0
    
    for len(queue) > 0 {
        depth++
        levelSize := len(queue)
        
        for i := 0; i < levelSize; i++ {
            node := queue[0]
            queue = queue[1:]
            
            // Check if this is a leaf node
            if node.Left == nil && node.Right == nil {
                return depth
            }
            
            if node.Left != nil {
                queue = append(queue, node.Left)
            }
            if node.Right != nil {
                queue = append(queue, node.Right)
            }
        }
    }
    
    return depth
}
