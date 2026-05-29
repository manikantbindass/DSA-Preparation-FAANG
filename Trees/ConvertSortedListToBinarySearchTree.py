"""
LeetCode Problem 109: Convert Sorted List to Binary Search Tree
Problem Number: 109
Difficulty: Medium
Link: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

Given the head of a singly linked list where elements are sorted in ascending order,
convert it into a height-balanced binary search tree.

Example 1:
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5] which represents the height-balanced BST.

Example 2:
Input: head = []
Output: []

Constraints:
- The number of nodes in the list is in the range [0, 2 * 10^4].
- -10^5 <= Node.val <= 10^5

Topics: Linked List, Divide and Conquer, Tree, Binary Search Tree, Binary Tree
Time Complexity: O(n) - building the list and constructing tree
Space Complexity: O(n) - for the list conversion and recursion stack
"""

from typing import List, Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        # Convert linked list to array list for O(1) access
        values = []
        curr = head
        while curr:
            values.append(curr.val)
            curr = curr.next
        
        def buildBST(left: int, right: int) -> Optional[TreeNode]:
            if left > right:
                return None
            # Choose middle element as root to maintain balance
            mid = (left + right) // 2
            root = TreeNode(values[mid])
            root.left = buildBST(left, mid - 1)
            root.right = buildBST(mid + 1, right)
            return root
        
        return buildBST(0, len(values) - 1)
