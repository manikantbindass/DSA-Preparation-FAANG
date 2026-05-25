"""
LeetCode Problem 99: Recover Binary Search Tree
Problem Number: 99
Difficulty: Medium
Link: https://leetcode.com/problems/recover-binary-search-tree/

You are given the root of a binary search tree (BST), where exactly two nodes 
of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: Swapping 1 and 3 recovers the BST.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: Swapping 2 and 3 recovers the BST.

Constraints:
- The number of nodes in the tree is in the range [2, 1000].
- -2^31 <= Node.val <= 2^31 - 1

Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(n) - for the recursion stack (worst-case for skewed tree)
"""

from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        self.prev = None
        self.first = None
        self.second = None
        
        def inorder(node: Optional[TreeNode]) -> None:
            if not node:
                return
            
            # Traverse left subtree
            inorder(node.left)
            
            # Check if current node violates BST property with previous node
            if self.prev and self.prev.val > node.val:
                if not self.first:
                    self.first = self.prev
                self.second = node
            self.prev = node
            
            # Traverse right subtree
            inorder(node.right)
        
        inorder(root)
        
        # Swap values to recover the BST
        if self.first and self.second:
            self.first.val, self.second.val = self.second.val, self.first.val
