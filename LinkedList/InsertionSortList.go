/*
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
*/

package linkedlist

import "math"

func insertionSortList(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }
    
    // Dummy node to simplify insertion at the beginning
    dummy := &ListNode{Val: math.MinInt32, Next: head}
    prev := head
    curr := head.Next
    
    for curr != nil {
        if prev.Val <= curr.Val {
            // Already in correct order
            prev = curr
            curr = curr.Next
            continue
        }
        
        // Find the correct position to insert curr
        insertPos := dummy
        for insertPos.Next.Val <= curr.Val {
            insertPos = insertPos.Next
        }
        
        // Remove curr from its current position
        prev.Next = curr.Next
        
        // Insert curr after insertPos
        curr.Next = insertPos.Next
        insertPos.Next = curr
        
        // Move curr to next node
        curr = prev.Next
    }
    
    return dummy.Next
}
