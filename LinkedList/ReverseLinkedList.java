// ──────────────────────────────────────────────────────────────────────
// LeetCode #206 · Reverse Linked List
// Difficulty : Easy
// Topics     : Linked List, Recursion
// URL        : https://leetcode.com/problems/reverse-linked-list/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The solution uses an iterative approach with a dummy node to reverse
//   the linked list. We traverse the original list, and for each node, we
//   insert it right after the dummy node, effectively building the
//   reversed list. This is done by storing the next node before changing
//   pointers. The dummy node simplifies edge cases and avoids special
//   handling for the head. The algorithm runs in O(n) time and uses O(1)
//   extra space.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42 MB
// 
// Examples
//   Example 1:
//     Input  : head = [1,2,3,4,5]
//     Output : [5,4,3,2,1]
//   Example 2:
//     Input  : head = [1,2]
//     Output : [2,1]
//   Example 3:
//     Input  : head = []
//     Output : []
// 
// Constraints
//   · The number of nodes in the list is the range [0, 5000].
//   · -5000 <= Node.val <= 5000
// ──────────────────────────────────────────────────────────────────────

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
