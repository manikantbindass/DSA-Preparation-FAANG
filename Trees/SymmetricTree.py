"""
LeetCode Problem 101: Symmetric Tree
Problem Number: 101
Difficulty: Easy
Link: https://leetcode.com/problems/symmetric-tree/

Given the root of a binary tree, check whether it is a mirror of itself
(i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
- The number of nodes in the tree is in the range [1, 1000].
- -100 <= Node.val <= 100

Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - where h is the height of the tree for recursion stack
"""

from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        # An empty tree or single node is symmetric
        if not root:
            return True
        # Check if left and right subtrees are mirrors of each other
        return self._isMirror(root.left, root.right)
    
    def _isMirror(self, left: Optional[TreeNode], right: Optional[TreeNode]) -> bool:
        # Both are None -> symmetric at this point
        if not left and not right:
            return True
        # One is None or values differ -> not symmetric
        if not left or not right or left.val != right.val:
            return False
        # Check: left's left with right's right AND left's right with right's left
        return self._isMirror(left.left, right.right) and self._isMirror(left.right, right.left)
