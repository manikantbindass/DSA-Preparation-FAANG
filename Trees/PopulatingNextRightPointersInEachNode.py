"""
LeetCode Problem 116: Populating Next Right Pointers in Each Node
Problem Number: 116
Difficulty: Medium
Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

You are given a perfect binary tree where all leaves are at the same level,
and every parent has two children. Populate each next pointer to point to its
next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]

Example 2:
Input: root = []
Output: []

Constraints:
- The number of nodes in the tree is in the range [0, 2^12 - 1].
- -1000 <= Node.val <= 1000

Topics: Linked List, Tree, Depth-First Search, Breadth-First Search, Binary Tree
Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - where w is the maximum width of the tree (queue size)
"""

from collections import deque

# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next

class Solution:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root:
            return root
        
        queue = deque([root])
        
        while queue:
            level_size = len(queue)
            prev = None
            
            for _ in range(level_size):
                curr = queue.popleft()
                
                # Connect previous node to current node
                if prev:
                    prev.next = curr
                prev = curr
                
                # Add children to queue
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
        
        return root
