/*
LeetCode Problem 82: Remove Duplicates from Sorted List II
Problem Number: 82
Difficulty: Medium
Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list. Return the linked list sorted as well.

Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
Explanation: Nodes with values 3 and 4 appear twice, so they are removed.

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]
Explanation: Nodes with value 1 appear three times, so they are removed.

Constraints:
- The number of nodes in the list is in the range [0, 300].
- -100 <= Node.val <= 100
- The list is guaranteed to be sorted in ascending order.

Topics: Linked List, Two Pointers
Time Complexity: O(n) - single pass through the list
Space Complexity: O(1) - only using constant extra space
*/

package linkedlist

// ListNode defines a node in a singly-linked list.
type ListNode struct {
    Val  int
    Next *ListNode
}

func deleteDuplicates(head *ListNode) *ListNode {
    dummy := &ListNode{Next: head}
    prev := dummy
    curr := head
    
    for curr != nil {
        // Skip all nodes with the same value as curr
        for curr.Next != nil && curr.Next.Val == curr.Val {
            curr = curr.Next
        }
        
        // If prev.Next is still curr, no duplicates were found
        if prev.Next == curr {
            prev = curr
        } else {
            // Duplicates found, skip all duplicate nodes
            prev.Next = curr.Next
        }
        
        curr = curr.Next
    }
    
    return dummy.Next
}
