/*
LeetCode Problem 86: Partition List
Problem Number: 86
Difficulty: Medium
Link: https://leetcode.com/problems/partition-list/

Given the head of a linked list and a value x, partition it such that all nodes
with values less than x come before nodes with values greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Explanation: Nodes less than 3 (1,2,2) come before nodes >= 3 (4,3,5),
             preserving relative order within each group.

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

Constraints:
- The number of nodes in the list is in the range [0, 200].
- -100 <= Node.val <= 100
- -100 <= x <= 100

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

func partition(head *ListNode, x int) *ListNode {
    // Create two dummy nodes to start the less-than and greater-or-equal lists
    lessHead := &ListNode{}
    greaterHead := &ListNode{}
    
    lessTail := lessHead
    greaterTail := greaterHead
    
    // Traverse the original list
    for curr := head; curr != nil; curr = curr.Next {
        if curr.Val < x {
            lessTail.Next = curr
            lessTail = lessTail.Next
        } else {
            greaterTail.Next = curr
            greaterTail = greaterTail.Next
        }
    }
    
    // Terminate the greater list
    greaterTail.Next = nil
    
    // Connect the two lists
    lessTail.Next = greaterHead.Next
    
    // Return the head of the combined list (skip the dummy node)
    return lessHead.Next
}
