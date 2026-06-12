/*
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
*/

package linkedlist

// ListNode defines a node in a singly-linked list.
type ListNode struct {
    Val  int
    Next *ListNode
}

func reorderList(head *ListNode) {
    if head == nil || head.Next == nil {
        return
    }
    
    // Step 1: Find the middle of the list
    slow := head
    fast := head
    for fast.Next != nil && fast.Next.Next != nil {
        slow = slow.Next
        fast = fast.Next.Next
    }
    
    // Step 2: Reverse the second half
    secondHalf := slow.Next
    slow.Next = nil
    var prev *ListNode
    curr := secondHalf
    for curr != nil {
        nextTemp := curr.Next
        curr.Next = prev
        prev = curr
        curr = nextTemp
    }
    reversedSecondHalf := prev
    
    // Step 3: Merge the two halves
    first := head
    second := reversedSecondHalf
    for second != nil {
        firstNext := first.Next
        secondNext := second.Next
        
        first.Next = second
        second.Next = firstNext
        
        first = firstNext
        second = secondNext
    }
}
