/*
 * LeetCode Problem 92: Reverse Linked List II
 * Problem Number: 92
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/reverse-linked-list-ii/
 * 
 * Given the head of a singly linked list and two integers left and right
 * where left <= right, reverse the nodes of the list from position left to
 * position right, and return the reversed list.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Explanation: Nodes at positions 2,3,4 are reversed.
 * 
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [1, 500].
 * - -500 <= Node.val <= 500
 * - 1 <= left <= right <= n
 * 
 * Topics: Linked List
 * Time Complexity: O(n) - single pass through the list
 * Space Complexity: O(1) - only using constant extra space
 */

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // If there's only one node or no reversal needed
        if (head.next == null || left == right) {
            return head;
        }
        
        // Dummy node to handle edge cases (e.g., reversing from head)
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        
        // Move prev to the node just before the reversal start
        for (int i = 0; i < left - 1; ++i) {
            prev = prev.next;
        }
        
        // Mark the start of the sublist to reverse and the node before it
        ListNode beforeReverse = prev;
        ListNode reverseStart = prev.next;
        
        ListNode curr = reverseStart;
        ListNode prevReversed = null;
        
        // Reverse the sublist from left to right
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode nextTemp = curr.next;
            curr.next = prevReversed;
            prevReversed = curr;
            curr = nextTemp;
        }
        
        // Connect the reversed sublist back to the rest of the list
        beforeReverse.next = prevReversed;
        reverseStart.next = curr;
        
        return dummy.next;
    }
}
