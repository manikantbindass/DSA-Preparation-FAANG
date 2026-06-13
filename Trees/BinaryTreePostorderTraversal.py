"""
LeetCode Problem 145: Binary Tree Postorder Traversal
Problem Number: 145
Difficulty: Easy
Link: https://leetcode.com/problems/binary-tree-postorder-traversal/

Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]

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
Space Complexity: O(n) - for the stack
"""

from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []
        
        result = []
        stack = [root]
        
        while stack:
            node = stack.pop()
            # Add to front of list (reverse order of preorder)
            result.append(node.val)
            
            # Push left first, then right (so right is processed first due to LIFO)
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)
        
        # Reverse to get postorder (left -> right -> root)
        return result[::-1]
