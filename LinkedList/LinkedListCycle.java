/*
 * LeetCode Problem 141: Linked List Cycle
 * Problem Number: 141
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/linked-list-cycle/
 * 
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * 
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer. Internally, pos is used to denote the index of the
 * node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * 
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * 
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - pos is -1 or a valid index in the linked list.
 * 
 * Topics: Hash Table, Linked List, Two Pointers
 * Time Complexity: O(n) - where n is the number of nodes
 * Space Complexity: O(1) - using Floyd's cycle detection algorithm
 */

import java.util.HashSet;
import java.util.Set;

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
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        // Floyd's cycle detection algorithm (Tortoise and Hare)
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
}
