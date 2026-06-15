/*
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
*/

package linkedlist

// ListNode defines a node in a singly-linked list.
type ListNode struct {
    Val  int
    Next *ListNode
}

func sortList(head *ListNode) *ListNode {
    // Base case: empty list or single node
    if head == nil || head.Next == nil {
        return head
    }
    
    // Step 1: Find the middle of the list using slow/fast pointers
    slow := head
    fast := head.Next
    for fast != nil && fast.Next != nil {
        slow = slow.Next
        fast = fast.Next.Next
    }
    
    // Step 2: Split the list into two halves
    secondHalf := slow.Next
    slow.Next = nil
    
    // Step 3: Recursively sort both halves
    left := sortList(head)
    right := sortList(secondHalf)
    
    // Step 4: Merge the two sorted halves
    return merge(left, right)
}

func merge(l1, l2 *ListNode) *ListNode {
    dummy := &ListNode{}
    tail := dummy
    
    for l1 != nil && l2 != nil {
        if l1.Val <= l2.Val {
            tail.Next = l1
            l1 = l1.Next
        } else {
            tail.Next = l2
            l2 = l2.Next
        }
        tail = tail.Next
    }
    
    // Append remaining nodes
    if l1 != nil {
        tail.Next = l1
    } else {
        tail.Next = l2
    }
    
    return dummy.Next
}
