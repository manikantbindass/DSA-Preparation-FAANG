/*
 * LeetCode Problem 2182: Find the Minimum and Maximum Number of Nodes Between Critical Points
 * Problem Number: 2182
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 *
 * A critical point in a linked list is defined as either a local maxima or a local
 * minima.
 *
 * A node is a local maxima if the current node has a value strictly greater than
 * the previous node and the next node.
 *
 * A node is a local minima if the current node has a value strictly smaller than
 * the previous node and the next node.
 *
 * Note that a node can only be a local maxima/minima if there exists both a
 * previous node and a next node.
 *
 * Given a linked list head, return an array of length 2 containing [minDistance,
 * maxDistance] where minDistance is the minimum distance between any two distinct
 * critical points and maxDistance is the maximum distance between any two distinct
 * critical points. If there are fewer than two critical points, return [-1, -1].
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,1]
 * Output: [-1,-1]
 * Explanation: There are no critical points in [3,1].
 *
 * Example 2:
 *
 * Input: head = [5,3,1,2,5,1,2]
 * Output: [1,3]
 * Explanation: There are three critical points:
 * - [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and
 * 2.
 * - [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2
 * and 1.
 * - [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and
 * 2.
 * The minimum distance is between the fifth and the sixth node. minDistance = 6 -
 * 5 = 1.
 * The maximum distance is between the third and the sixth node. maxDistance = 6 -
 * 3 = 3.
 *
 * Example 3:
 *
 * Input: head = [1,3,2,2,3,2,2,2,7]
 * Output: [3,3]
 * Explanation: There are two critical points:
 * - [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater
 * than 1 and 2.
 * - [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater
 * than 2 and 2.
 * Both the minimum and maximum distances are between the second and the fifth
 * node.
 * Thus, minDistance and maxDistance is 5 - 2 = 3.
 * Note that the last node is not considered a local maxima because it does not
 * have a next node.
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the list is in the range [2, 105].
 * 	1 <= Node.val <= 105
 *
 * Example 1:
 * Input: head = [3,1]
 * Output: [-1,-1]
 * Explanation: There are no critical points in [3,1].
 *
 * Example 2:
 * Input: head = [5,3,1,2,5,1,2]
 * Output: [1,3]
 * Explanation: There are three critical points:
 *
 * Example 3:
 * Input: head = [1,3,2,2,3,2,2,2,7]
 * Output: [3,3]
 * Explanation: There are two critical points:
 *
 * Constraints:
 * - The number of nodes in the list is in the range [2, 105].
 * - 1 <= Node.val <= 105
 *
 * Topics: Linked List
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * /**
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode() {}
 *  *     ListNode(int val) { this.val = val; }
 *  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 *  * }
 *  */
 * class Solution {
 *     public int[] nodesBetweenCriticalPoints(ListNode head) {
 *         int[] ans= {1 << 30, 0};
 *         int first= -1, last = -1;
 *         for (int i = 0; head.next.next != null; head = head.next, ++i) {
 *             int a = head.val, b = head.next.val, c = head.next.next.val;
 *             if (b < Math.min(a, c) || b > Math.max(a, c)) {
 *                 if (last==-1) {
 *                     first = i;
 *                     last = i;
 *                 } else {
 *                     ans[0]= Math.min(ans[0], i - last);
 *                     last = i;
 *                     ans[1] = Math.max(ans[1], last - first);
 *                 }
 *             }
 *         }
 *         return first==last ? new int[] {-1, -1} : ans;
 *     }
 * }
 */

package findtheminimumandmaximumnumberofnodesbetweencriticalpoints

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
