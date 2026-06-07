"""
LeetCode Problem 2196: Create Binary Tree From Descriptions
Problem Number: 2196
Difficulty: Medium
Link: https://leetcode.com/problems/create-binary-tree-from-descriptions/

You are given a 2D integer array descriptions where descriptions[i] = [parent_i, child_i, isLeft_i]
indicates that parent_i is the parent of child_i in a binary tree.

Return the root of the binary tree.

Example 1:
Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]

Example 2:
Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]

Constraints:
- 1 <= descriptions.length <= 10^4
- descriptions[i].length == 3
- 1 <= parent_i, child_i <= 10^5
- 0 <= isLeft_i <= 1

Topics: Array, Hash Table, Tree, Binary Tree
Time Complexity: O(n) - single pass through descriptions
Space Complexity: O(n) - for storing nodes and children set
"""

from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        node_map = {}
        children = set()
        
        for parent_val, child_val, is_left in descriptions:
            # Get or create parent node
            if parent_val not in node_map:
                node_map[parent_val] = TreeNode(parent_val)
            # Get or create child node
            if child_val not in node_map:
                node_map[child_val] = TreeNode(child_val)
            
            parent = node_map[parent_val]
            child = node_map[child_val]
            
            # Set left or right child
            if is_left == 1:
                parent.left = child
            else:
                parent.right = child
            
            # Mark child as having a parent
            children.add(child_val)
        
        # Find the root (node that is never a child)
        for val, node in node_map.items():
            if val not in children:
                return node
        
        return None
