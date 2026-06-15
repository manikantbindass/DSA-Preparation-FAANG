/*
 * LeetCode Problem 2095: Delete the Middle Node of a Linked List
 * Problem Number: 2095
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * 
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified list.
 * 
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
 * where ⌊x⌋ denotes the largest integer less than or equal to x.
 * 
 * Example 1:
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation: The middle node is 7 (index 3), which is deleted.
 * 
 * Example 2:
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation: The middle node is 3 (index 2), which is deleted.
 * 
 * Example 3:
 * Input: head = [2,1]
 * Output: [2]
 * Explanation: The middle node is 1 (index 1), which is deleted.
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5].
 * - 1 <= Node.val <= 10^5
 * 
 * Topics: Linked List, Two Pointers
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
    public ListNode deleteMiddle(ListNode head) {
        // If only one node, return null
        if (head == null || head.next == null) {
            return null;
        }
        
        // Dummy node to handle edge cases
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;
        
        // Fast moves twice as fast as slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete the middle node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
