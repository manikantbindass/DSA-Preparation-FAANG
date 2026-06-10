"""
LeetCode Problem 138: Copy List with Random Pointer
Problem Number: 138
Difficulty: Medium
Link: https://leetcode.com/problems/copy-list-with-random-pointer/

A linked list of length n is given such that each node contains an additional random pointer,
which could point to any node in the list or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list.

Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Constraints:
- 0 <= n <= 1000
- -10^4 <= Node.val <= 10^4
- Node.random is null or points to some node in the linked list.

Topics: Hash Table, Linked List
Time Complexity: O(n) - single pass to copy nodes and assign random pointers
Space Complexity: O(n) - for the hash map
"""

from typing import Optional

# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return None
        
        # Map original nodes to their copies
        node_map = {}
        
        # First pass: create all nodes without random pointers
        current = head
        while current:
            node_map[current] = Node(current.val)
            current = current.next
        
        # Second pass: assign next and random pointers
        current = head
        while current:
            copy = node_map[current]
            copy.next = node_map.get(current.next)
            copy.random = node_map.get(current.random)
            current = current.next
        
        return node_map[head]
