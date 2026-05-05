// LeetCode 61 - Rotate List
// Time Complexity: O(n) | Space Complexity: O(1)
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int length = 0;
        for (ListNode current = head; current != null; current = current.next) {
            length++;
        }

        k %= length;
        if (k == 0) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (k-- > 0) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode answer = slow.next;
        slow.next = null;
        fast.next = head;
        return answer;
    }
}
