/*
 * LeetCode Problem 148: Sort List
 * Problem Number: 148
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/sort-list/
 * 
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * 
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * 
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * 
 * Example 3:
 * Input: head = []
 * Output: []
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5 * 10^4].
 * - -10^5 <= Node.val <= 10^5
 * 
 * Topics: Linked List, Two Pointers, Divide and Conquer, Sorting, Merge Sort
 * Time Complexity: O(n log n) - where n is the number of nodes
 * Space Complexity: O(log n) - for the recursion stack
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
    public ListNode sortList(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 1: Find the middle of the list using slow/fast pointers
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Split the list into two halves
        ListNode secondHalf = slow.next;
        slow.next = null;
        
        // Step 3: Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);
        
        // Step 4: Merge the two sorted halves
        return merge(left, right);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        // Append remaining nodes
        tail.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
}
