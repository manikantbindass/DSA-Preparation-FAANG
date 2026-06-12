"""
LeetCode Problem 143: Reorder List
Problem Number: 143
Difficulty: Medium
Link: https://leetcode.com/problems/reorder-list/

You are given the head of a singly linked list. The list can be represented as:
L0 → L1 → … → Ln-1 → Ln
Reorder the list to be on the following form:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:
- The number of nodes in the list is in the range [1, 5 * 10^4].
- 1 <= Node.val <= 1000

Topics: Linked List, Two Pointers, Stack, Recursion
Time Complexity: O(n) - where n is the number of nodes
Space Complexity: O(1) - only using constant extra space
"""

from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if not head or not head.next:
            return
        
        # Step 1: Find the middle of the list
        slow = head
        fast = head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        
        # Step 2: Reverse the second half
        second_half = slow.next
        slow.next = None
        prev = None
        curr = second_half
        while curr:
            next_temp = curr.next
            curr.next = prev
            prev = curr
            curr = next_temp
        reversed_second_half = prev
        
        # Step 3: Merge the two halves
        first = head
        second = reversed_second_half
        while second:
            first_next = first.next
            second_next = second.next
            
            first.next = second
            second.next = first_next
            
            first = first_next
            second = second_next
