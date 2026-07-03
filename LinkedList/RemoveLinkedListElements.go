// ──────────────────────────────────────────────────────────────────────
// LeetCode #203 · Remove Linked List Elements
// Difficulty : Easy
// Topics     : Linked List, Recursion
// URL        : https://leetcode.com/problems/remove-linked-list-elements/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to remove all nodes with a given value from a singly
//   linked list. The approach uses a dummy node that points to the head to
//   handle edge cases where the head itself needs to be removed. We
//   iterate through the list with two pointers: prev (starting at dummy)
//   and curr (starting at head). For each node, if its value equals val,
//   we skip it by linking prev.next to curr.next; otherwise, we move prev
//   to curr. Then we advance curr to curr.next. Finally, we return
//   dummy.next, which is the new head after removals.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : head = [1,2,6,3,4,5,6], val = 6
//     Output : [1,2,3,4,5]
//   Example 2:
//     Input  : head = [], val = 1
//     Output : []
//   Example 3:
//     Input  : head = [7,7,7,7], val = 7
//     Output : []
// 
// Constraints
//   · The number of nodes in the list is in the range [0, 104].
//   · 1 <= Node.val <= 50
//   · 0 <= val <= 50
// ──────────────────────────────────────────────────────────────────────

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeElements(head *ListNode, val int) *ListNode {
    dummy := &ListNode{Next: head}
    prev := dummy
    curr := head
    for curr != nil {
        if curr.Val == val {
            prev.Next = curr.Next
        } else {
            prev = curr
        }
        curr = curr.Next
    }
    return dummy.Next
}
