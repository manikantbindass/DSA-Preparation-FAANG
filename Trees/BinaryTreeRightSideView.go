// ──────────────────────────────────────────────────────────────────────
// LeetCode #199 · Binary Tree Right Side View
// Difficulty : Medium
// Topics     : Tree, Depth-First Search, Breadth-First Search, Binary Tree
// URL        : https://leetcode.com/problems/binary-tree-right-side-view/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We perform a level-order traversal (BFS) of the binary tree. For each
//   level, we add the value of the first node in the queue (which is the
//   rightmost node because we push right child before left child) to the
//   result list. Then we process all nodes of the current level by polling
//   them and enqueuing their right and left children (in that order) for
//   the next level. This ensures that at each level, the first node we see
//   is the rightmost node.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : root = [1,2,3,null,5,null,4]
//     Output : [1,3,4]
//   Example 2:
//     Input  : root = [1,2,3,4,null,null,null,5]
//     Output : [1,3,4,5]
//   Example 3:
//     Input  : root = [1,null,3]
//     Output : [1,3]
//   Example 4:
//     Input  : root = []
//     Output : []
// 
// Constraints
//   · The number of nodes in the tree is in the range [0, 100].
//   · -100 <= Node.val <= 100
// ──────────────────────────────────────────────────────────────────────

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rightSideView(root *TreeNode) []int {
    result := []int{}
    if root == nil {
        return result
    }
    queue := []*TreeNode{root}
    for len(queue) > 0 {
        levelSize := len(queue)
        for i := 0; i < levelSize; i++ {
            node := queue[0]
            queue = queue[1:]
            if i == 0 {
                result = append(result, node.Val)
            }
            if node.Right != nil {
                queue = append(queue, node.Right)
            }
            if node.Left != nil {
                queue = append(queue, node.Left)
            }
        }
    }
    return result
}
