/*
 * LeetCode Problem 147: Insertion Sort List
 * Problem Number: 147
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/insertion-sort-list/
 * 
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 * 
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * 
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [1, 5000].
 * - -5000 <= Node.val <= 5000
 * 
 * Topics: Linked List, Sorting
 * Time Complexity: O(n²) - where n is the number of nodes
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Dummy node to simplify insertion at the beginning
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        ListNode prev = head;
        ListNode curr = head.next;
        
        while (curr != null) {
            if (prev.val <= curr.val) {
                // Already in correct order
                prev = curr;
                curr = curr.next;
                continue;
            }
            
            // Find the correct position to insert curr
            ListNode insertPos = dummy;
            while (insertPos.next.val <= curr.val) {
                insertPos = insertPos.next;
            }
            
            // Remove curr from its current position
            prev.next = curr.next;
            
            // Insert curr after insertPos
            curr.next = insertPos.next;
            insertPos.next = curr;
            
            // Move curr to next node
            curr = prev.next;
        }
        
        return dummy.next;
    }
}
