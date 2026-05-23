/*
LeetCode Problem 95: Unique Binary Search Trees II
Problem Number: 95
Difficulty: Medium
Link: https://leetcode.com/problems/unique-binary-search-trees-ii/

Given an integer n, return all the structurally unique BST's (binary search trees),
which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
- 1 <= n <= 8

Topics: Dynamic Programming, Backtracking, Tree, Binary Search Tree, Binary Tree
Time Complexity: O(C_n * n) where C_n is the nth Catalan number
Space Complexity: O(C_n * n) - for storing all trees
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func generateTrees(n int) []*TreeNode {
    if n == 0 {
        return []*TreeNode{}
    }
    return buildTrees(1, n)
}

func buildTrees(start, end int) []*TreeNode {
    result := make([]*TreeNode, 0)
    
    // Base case: empty subtree
    if start > end {
        result = append(result, nil)
        return result
    }
    
    // Try each value as the root
    for rootVal := start; rootVal <= end; rootVal++ {
        // Generate all left subtrees
        leftTrees := buildTrees(start, rootVal-1)
        // Generate all right subtrees
        rightTrees := buildTrees(rootVal+1, end)
        
        // Combine each left and right subtree with the root
        for _, left := range leftTrees {
            for _, right := range rightTrees {
                root := &TreeNode{Val: rootVal, Left: left, Right: right}
                result = append(result, root)
            }
        }
    }
    
    return result
}
