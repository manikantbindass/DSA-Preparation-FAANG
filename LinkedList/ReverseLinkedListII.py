"""
LeetCode Problem 92: Reverse Linked List II
Problem Number: 92
Difficulty: Medium
Link: https://leetcode.com/problems/reverse-linked-list-ii/

Given the head of a singly linked list and two integers left and right
where left <= right, reverse the nodes of the list from position left to
position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Explanation: Nodes at positions 2,3,4 are reversed.

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

Constraints:
- The number of nodes in the list is in the range [1, 500].
- -500 <= Node.val <= 500
- 1 <= left <= right <= n

Topics: Linked List
Time Complexity: O(n) - single pass through the list
Space Complexity: O(1) - only using constant extra space
"""

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseBetween(self, head: ListNode, left: int, right: int) -> ListNode:
        # If there's only one node or no reversal needed
        if not head.next or left == right:
            return head
        
        # Dummy node to handle edge cases (e.g., reversing from head)
        dummy = ListNode(0, head)
        prev = dummy
        
        # Move prev to the node just before the reversal start
        for _ in range(left - 1):
            prev = prev.next
        
        # Mark the start of the sublist to reverse and the node before it
        before_reverse = prev
        reverse_start = prev.next
        
        curr = reverse_start
        prev_reversed = None
        
        # Reverse the sublist from left to right
        for _ in range(right - left + 1):
            next_temp = curr.next
            curr.next = prev_reversed
            prev_reversed = curr
            curr = next_temp
        
        # Connect the reversed sublist back to the rest of the list
        before_reverse.next = prev_reversed
        reverse_start.next = curr
        
        return dummy.next
