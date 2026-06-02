/*
LeetCode Problem 117: Populating Next Right Pointers in Each Node II
Problem Number: 117
Difficulty: Medium
Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

Given a binary tree, populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]

Example 2:
Input: root = []
Output: []

Constraints:
- The number of nodes in the tree is in the range [0, 6000].
- -100 <= Node.val <= 100

Topics: Linked List, Tree, Depth-First Search, Breadth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - where w is the maximum width of the tree (queue size)
*/

package trees

// Node defines a node in the binary tree with a next pointer.
type Node struct {
    Val   int
    Left  *Node
    Right *Node
    Next  *Node
}

func connect(root *Node) *Node {
    if root == nil {
        return root
    }
    
    queue := []*Node{root}
    
    for len(queue) > 0 {
        levelSize := len(queue)
        var prev *Node
        
        for i := 0; i < levelSize; i++ {
            curr := queue[0]
            queue = queue[1:]
            
            // Connect previous node to current node
            if prev != nil {
                prev.Next = curr
            }
            prev = curr
            
            // Add children to queue
            if curr.Left != nil {
                queue = append(queue, curr.Left)
            }
            if curr.Right != nil {
                queue = append(queue, curr.Right)
            }
        }
    }
    
    return root
}
