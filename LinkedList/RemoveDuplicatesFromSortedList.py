"""
LeetCode Problem 83: Remove Duplicates from Sorted List
Problem Number: 83
Difficulty: Easy
Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list/

Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
Return the linked list sorted as well.

Example 1:
Input: head = [1,1,2]
Output: [1,2]
Explanation: The duplicate 1 is removed.

Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]
Explanation: Duplicates 1 and 3 are removed.

Constraints:
- The number of nodes in the list is in the range [0, 300].
- -100 <= Node.val <= 100
- The list is guaranteed to be sorted in ascending order.

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
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        curr = head
        while curr and curr.next:
            if curr.val == curr.next.val:
                curr.next = curr.next.next
            else:
                curr = curr.next
        return head
