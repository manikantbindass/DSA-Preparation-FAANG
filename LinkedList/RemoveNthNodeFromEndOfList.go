// LeetCode 19 - Remove Nth Node From End of List
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	dummy := &ListNode{Next: head}
	fast := dummy
	slow := dummy

	for n > 0 {
		fast = fast.Next
		n--
	}

	for fast.Next != nil {
		slow = slow.Next
		fast = fast.Next
	}

	slow.Next = slow.Next.Next
	return dummy.Next
}
