# LeetCode 19 - Remove Nth Node From End of List
# Time Complexity: O(n) | Space Complexity: O(1)
from typing import Optional


class Solution:
    def removeNthFromEnd(self, head: Optional["ListNode"], n: int) -> Optional["ListNode"]:
        dummy = ListNode(0, head)
        fast = dummy
        slow = dummy

        while n > 0:
            fast = fast.next
            n -= 1

        while fast.next is not None:
            slow = slow.next
            fast = fast.next

        slow.next = slow.next.next
        return dummy.next
