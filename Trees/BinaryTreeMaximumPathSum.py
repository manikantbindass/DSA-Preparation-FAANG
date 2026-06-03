"""
LeetCode Problem 124: Binary Tree Maximum Path Sum
Problem Number: 124
Difficulty: Hard
Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes has an edge connecting them.
A node can only appear in the sequence at most once. The path does not need to pass through the root.

The path sum of a path is the sum of the node's values along the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with sum 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with sum 42.

Constraints:
- The number of nodes in the tree is in the range [1, 3 * 10^4].
- -1000 <= Node.val <= 1000

Topics: Tree, Depth-First Search, Dynamic Programming, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - where h is the height of the tree (recursion stack)
"""

from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.max_sum = float('-inf')
        
        def dfs(node: Optional[TreeNode]) -> int:
            if not node:
                return 0
            
            # Get maximum sum from left and right subtrees (only if positive)
            left_sum = max(0, dfs(node.left))
            right_sum = max(0, dfs(node.right))
            
            # Update global maximum with path passing through current node
            self.max_sum = max(self.max_sum, node.val + left_sum + right_sum)
            
            # Return maximum sum of path starting from current node going down to children
            return node.val + max(left_sum, right_sum)
        
        dfs(root)
        return self.max_sum
