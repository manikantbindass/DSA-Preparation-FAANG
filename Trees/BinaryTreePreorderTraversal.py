"""
LeetCode Problem 144: Binary Tree Preorder Traversal
Problem Number: 144
Difficulty: Easy
Link: https://leetcode.com/problems/binary-tree-preorder-traversal/

Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

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
"""

from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result = []
        
        def dfs(node: Optional[TreeNode]) -> None:
            if not node:
                return
            # Preorder: root -> left -> right
            result.append(node.val)
            dfs(node.left)
            dfs(node.right)
        
        dfs(root)
        return result
