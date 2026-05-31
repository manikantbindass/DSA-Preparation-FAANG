"""
LeetCode Problem 112: Path Sum
Problem Number: 112
Difficulty: Easy
Link: https://leetcode.com/problems/path-sum/

Given the root of a binary tree and an integer targetSum, return true if the tree has a 
root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The path 5 -> 4 -> 11 -> 2 sums to 22.

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false

Example 3:
Input: root = [], targetSum = 0
Output: false

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -1000 <= Node.val <= 1000
- -1000 <= targetSum <= 1000

Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
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
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        return self._dfs(root, targetSum)
    
    def _dfs(self, node: Optional[TreeNode], remaining_sum: int) -> bool:
        if not node:
            return False
        
        # Subtract current node's value from remaining sum
        remaining_sum -= node.val
        
        # Check if it's a leaf node and remaining sum is zero
        if not node.left and not node.right:
            return remaining_sum == 0
        
        # Recursively check left and right subtrees
        return self._dfs(node.left, remaining_sum) or self._dfs(node.right, remaining_sum)
