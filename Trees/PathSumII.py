"""
LeetCode Problem 113: Path Sum II
Problem Number: 113
Difficulty: Medium
Link: https://leetcode.com/problems/path-sum-ii/

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
where the sum of the node values in the path equals targetSum.

A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:
Input: root = [1,2], targetSum = 0
Output: []

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -1000 <= Node.val <= 1000
- -1000 <= targetSum <= 1000

Topics: Tree, Depth-First Search, Backtracking, Binary Tree
Time Complexity: O(n^2) in worst case (copying lists), O(n) for traversal
Space Complexity: O(h) - where h is the height of the tree (recursion stack)
"""

from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        result = []
        current_path = []
        
        def dfs(node: Optional[TreeNode], remaining_sum: int) -> None:
            if not node:
                return
            
            # Add current node to path
            current_path.append(node.val)
            remaining_sum -= node.val
            
            # Check if it's a leaf node and remaining sum is zero
            if not node.left and not node.right and remaining_sum == 0:
                result.append(current_path.copy())
            
            # Recursively traverse left and right subtrees
            dfs(node.left, remaining_sum)
            dfs(node.right, remaining_sum)
            
            # Backtrack: remove current node from path
            current_path.pop()
        
        dfs(root, targetSum)
        return result
