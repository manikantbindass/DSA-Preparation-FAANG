/*
 * LeetCode Problem 2130: Maximum Twin Sum of a Linked List
 * Problem Number: 2130
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
 * 
 * In a linked list of size n, where n is even, the ith node (0-indexed) is the twin of the (n-1-i)th node.
 * The twin sum is defined as the sum of a node and its twin.
 * 
 * Given the head of a linked list with an even number of nodes, return the maximum twin sum.
 * 
 * Example 1:
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation: Twin pairs: (5,1) -> 6, (4,2) -> 6. Maximum = 6.
 * 
 * Example 2:
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation: Twin pairs: (4,3) -> 7, (2,2) -> 4. Maximum = 7.
 * 
 * Example 3:
 * Input: head = [1,100000]
 * Output: 100001
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [2, 10^5].
 * - The number of nodes is even.
 * - 1 <= Node.val <= 10^5
 * 
 * Topics: Linked List, Two Pointers, Stack
 * Time Complexity: O(n) - where n is the number of nodes
 * Space Complexity: O(1) - using constant extra space (or O(n) for array approach)
 */

import java.util.ArrayList;
import java.util.List;

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
    public int pairSum(ListNode head) {
        List<Integer> values = new ArrayList<>();
        
        // Store all values in a list
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        int n = values.size();
        int maxSum = 0;
        
        // Calculate twin sums
        for (int i = 0; i < n / 2; i++) {
            int twinSum = values.get(i) + values.get(n - 1 - i);
            maxSum = Math.max(maxSum, twinSum);
        }
        
        return maxSum;
    }
}
