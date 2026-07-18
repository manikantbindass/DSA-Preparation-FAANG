/*
 * LeetCode Problem 234: Palindrome Linked List
 * Problem Number: 234
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/palindrome-linked-list/
 *
 * Given the head of a singly linked list, return true if it is a palindrome or
 * false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the list is in the range [1, 105].
 * 	0 <= Node.val <= 9
 *
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 105].
 * - 0 <= Node.val <= 9
 *
 * Topics: Linked List, Two Pointers, Stack, Recursion
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
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
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow =slow.next;
            fast = fast.next.next;
        }
        ListNode cur= slow.next;
        slow.next = null;
        ListNode pre = null;
        while (cur != null) {
            ListNode t= cur.next;
            cur.next = pre;
            pre= cur;
            cur= t;
        }
        while (pre != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre =pre.next;
            head = head.next;
        }
        return true;
    }
}
