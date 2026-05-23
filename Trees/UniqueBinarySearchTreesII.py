"""
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
"""

from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        if n == 0:
            return []
        
        def buildTrees(start: int, end: int) -> List[Optional[TreeNode]]:
            result = []
            
            # Base case: empty subtree
            if start > end:
                result.append(None)
                return result
            
            # Try each value as the root
            for root_val in range(start, end + 1):
                # Generate all left subtrees
                left_trees = buildTrees(start, root_val - 1)
                # Generate all right subtrees
                right_trees = buildTrees(root_val + 1, end)
                
                # Combine each left and right subtree with the root
                for left in left_trees:
                    for right in right_trees:
                        root = TreeNode(root_val, left, right)
                        result.append(root)
            
            return result
        
        return buildTrees(1, n)
