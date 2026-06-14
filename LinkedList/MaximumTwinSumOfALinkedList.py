"""
LeetCode Problem 2130: Maximum Twin Sum of a Linked List
Problem Number: 2130
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/

In a linked list of size n, where n is even, the ith node (0-indexed) is the twin of the (n-1-i)th node.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with an even number of nodes, return the maximum twin sum.

Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation: Twin pairs: (5,1) -> 6, (4,2) -> 6. Maximum = 6.

Example 2:
Input: head = [4,2,2,3]
Output: 7
Explanation: Twin pairs: (4,3) -> 7, (2,2) -> 4. Maximum = 7.

Example 3:
Input: head = [1,100000]
Output: 100001

Constraints:
- The number of nodes in the list is in the range [2, 10^5].
- The number of nodes is even.
- 1 <= Node.val <= 10^5

Topics: Linked List, Two Pointers, Stack
Time Complexity: O(n) - where n is the number of nodes
Space Complexity: O(1) - using constant extra space (or O(n) for array approach)
"""

from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def pairSum(self, head: Optional[ListNode]) -> int:
        values = []
        
        # Store all values in a list
        current = head
        while current:
            values.append(current.val)
            current = current.next
        
        n = len(values)
        max_sum = 0
        
        # Calculate twin sums
        for i in range(n // 2):
            twin_sum = values[i] + values[n - 1 - i]
            max_sum = max(max_sum, twin_sum)
        
        return max_sum
