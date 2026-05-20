/*
 * LeetCode Problem 86: Partition List
 * Problem Number: 86
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/partition-list/
 * 
 * Given the head of a linked list and a value x, partition it such that all nodes
 * with values less than x come before nodes with values greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Explanation: Nodes less than 3 (1,2,2) come before nodes >= 3 (4,3,5),
 *              preserving relative order within each group.
 * 
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 200].
 * - -100 <= Node.val <= 100
 * - -100 <= x <= 100
 * 
 * Topics: Linked List, Two Pointers
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
    public ListNode partition(ListNode head, int x) {
        // Create two dummy nodes to start the less-than and greater-or-equal lists
        ListNode lessHead = new ListNode();
        ListNode greaterHead = new ListNode();
        
        ListNode lessTail = lessHead;
        ListNode greaterTail = greaterHead;
        
        // Traverse the original list
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val < x) {
                lessTail.next = curr;
                lessTail = lessTail.next;
            } else {
                greaterTail.next = curr;
                greaterTail = greaterTail.next;
            }
        }
        
        // Terminate the greater list
        greaterTail.next = null;
        
        // Connect the two lists
        lessTail.next = greaterHead.next;
        
        // Return the head of the combined list (skip the dummy node)
        return lessHead.next;
    }
}
