"""
LeetCode Problem 147: Insertion Sort List
Problem Number: 147
Difficulty: Medium
Link: https://leetcode.com/problems/insertion-sort-list/

Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Constraints:
- The number of nodes in the list is in the range [1, 5000].
- -5000 <= Node.val <= 5000

Topics: Linked List, Sorting
Time Complexity: O(n²) - where n is the number of nodes
Space Complexity: O(1) - only using constant extra space
"""

from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        
        # Dummy node to simplify insertion at the beginning
        dummy = ListNode(float('-inf'), head)
        prev = head
        curr = head.next
        
        while curr:
            if prev.val <= curr.val:
                # Already in correct order
                prev = curr
                curr = curr.next
                continue
            
            # Find the correct position to insert curr
            insert_pos = dummy
            while insert_pos.next.val <= curr.val:
                insert_pos = insert_pos.next
            
            # Remove curr from its current position
            prev.next = curr.next
            
            # Insert curr after insert_pos
            curr.next = insert_pos.next
            insert_pos.next = curr
            
            # Move curr to next node
            curr = prev.next
        
        return dummy.next
