"""
LeetCode Problem 110: Balanced Binary Tree
Problem Number: 110
Difficulty: Easy
Link: https://leetcode.com/problems/balanced-binary-tree/

Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees
of every node never differs by more than one.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -10^4 <= Node.val <= 10^4

Topics: Tree, Depth-First Search, Binary Tree
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
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        # If height returns -1, tree is unbalanced
        return self._getHeight(root) != -1
    
    def _getHeight(self, node: Optional[TreeNode]) -> int:
        if not node:
            return 0
        
        # Get height of left subtree
        left_height = self._getHeight(node.left)
        # If left subtree is unbalanced, propagate -1 up
        if left_height == -1:
            return -1
        
        # Get height of right subtree
        right_height = self._getHeight(node.right)
        # If right subtree is unbalanced, propagate -1 up
        if right_height == -1:
            return -1
        
        # Check if current node is balanced
        if abs(left_height - right_height) > 1:
            return -1
        
        # Return height of current node
        return 1 + max(left_height, right_height)
