/*
 * LeetCode Problem 142: Linked List Cycle II
 * Problem Number: 142
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/linked-list-cycle-ii/
 * 
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * 
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * 
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - pos is -1 or a valid index in the linked list.
 * 
 * Topics: Hash Table, Linked List, Two Pointers
 * Time Complexity: O(n) - where n is the number of nodes
 * Space Complexity: O(1) - using Floyd's algorithm
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Phase 1: Detect if there is a cycle using Floyd's algorithm
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // Cycle detected, find the start of the cycle
                ListNode start = head;
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start;
            }
        }
        
        return null;
    }
}
