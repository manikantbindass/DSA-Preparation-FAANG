# LeetCode 61 - Rotate List
# Time Complexity: O(n) | Space Complexity: O(1)
from typing import Optional


class Solution:
    def rotateRight(self, head: Optional["ListNode"], k: int) -> Optional["ListNode"]:
        if head is None or head.next is None:
            return head

        length = 0
        current = head
        while current is not None:
            length += 1
            current = current.next

        k %= length
        if k == 0:
            return head

        fast = head
        slow = head

        while k > 0:
            fast = fast.next
            k -= 1

        while fast.next is not None:
            fast = fast.next
            slow = slow.next

        answer = slow.next
        slow.next = None
        fast.next = head
        return answer
