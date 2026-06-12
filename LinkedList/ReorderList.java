/*
 * LeetCode Problem 143: Reorder List
 * Problem Number: 143
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/reorder-list/
 * 
 * You are given the head of a singly linked list. The list can be represented as:
 * L0 → L1 → … → Ln-1 → Ln
 * Reorder the list to be on the following form:
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [1, 5 * 10^4].
 * - 1 <= Node.val <= 1000
 * 
 * Topics: Linked List, Two Pointers, Stack, Recursion
 * Time Complexity: O(n) - where n is the number of nodes
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Step 1: Find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse the second half
        ListNode secondHalf = slow.next;
        slow.next = null;
        ListNode prev = null;
        ListNode curr = secondHalf;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        ListNode reversedSecondHalf = prev;
        
        // Step 3: Merge the two halves
        ListNode first = head;
        ListNode second = reversedSecondHalf;
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            
            first.next = second;
            second.next = firstNext;
            
            first = firstNext;
            second = secondNext;
        }
    }
}
