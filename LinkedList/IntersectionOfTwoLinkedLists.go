/*
LeetCode Problem 160: Intersection of Two Linked Lists
Problem Number: 160
Difficulty: Easy
Link: https://leetcode.com/problems/intersection-of-two-linked-lists/

Given the heads of two singly linked lists headA and headB, return the node at which 
the two lists intersect. If the two linked lists have no intersection at all, return null.

Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'

Example 2:
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'

Example 3:
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection

Constraints:
- The number of nodes of listA is in the range [1, 3 * 10^4].
- The number of nodes of listB is in the range [1, 3 * 10^4].
- 1 <= Node.val <= 10^5

Topics: Hash Table, Linked List, Two Pointers
Time Complexity: O(n + m) - where n and m are lengths of the two lists
Space Complexity: O(1) - only using constant extra space
*/

package linkedlist

// ListNode defines a node in a singly-linked list.
type ListNode struct {
    Val  int
    Next *ListNode
}

func getIntersectionNode(headA, headB *ListNode) *ListNode {
    if headA == nil || headB == nil {
        return nil
    }
    
    // Two pointers to traverse both lists
    ptrA := headA
    ptrB := headB
    
    // When ptrA reaches the end, redirect to headB
    // When ptrB reaches the end, redirect to headA
    // They will meet at the intersection or at nil (no intersection)
    for ptrA != ptrB {
        if ptrA == nil {
            ptrA = headB
        } else {
            ptrA = ptrA.Next
        }
        
        if ptrB == nil {
            ptrB = headA
        } else {
            ptrB = ptrB.Next
        }
    }
    
    return ptrA
}
