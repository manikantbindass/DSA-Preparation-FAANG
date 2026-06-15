"""
LeetCode Problem 148: Sort List
Problem Number: 148
Difficulty: Medium
Link: https://leetcode.com/problems/sort-list/

Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []

Constraints:
- The number of nodes in the list is in the range [0, 5 * 10^4].
- -10^5 <= Node.val <= 10^5

Topics: Linked List, Two Pointers, Divide and Conquer, Sorting, Merge Sort
Time Complexity: O(n log n) - where n is the number of nodes
Space Complexity: O(log n) - for the recursion stack
"""

from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # Base case: empty list or single node
        if not head or not head.next:
            return head
        
        # Step 1: Find the middle of the list using slow/fast pointers
        slow = head
        fast = head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        
        # Step 2: Split the list into two halves
        second_half = slow.next
        slow.next = None
        
        # Step 3: Recursively sort both halves
        left = self.sortList(head)
        right = self.sortList(second_half)
        
        # Step 4: Merge the two sorted halves
        return self._merge(left, right)
    
    def _merge(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(0)
        tail = dummy
        
        while l1 and l2:
            if l1.val <= l2.val:
                tail.next = l1
                l1 = l1.next
            else:
                tail.next = l2
                l2 = l2.next
            tail = tail.next
        
        # Append remaining nodes
        tail.next = l1 if l1 else l2
        
        return dummy.next
