// LeetCode 61 - Rotate List
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	length := 0
	for current := head; current != nil; current = current.Next {
		length++
	}

	k %= length
	if k == 0 {
		return head
	}

	fast := head
	slow := head

	for k > 0 {
		fast = fast.Next
		k--
	}

	for fast.Next != nil {
		fast = fast.Next
		slow = slow.Next
	}

	answer := slow.Next
	slow.Next = nil
	fast.Next = head
	return answer
}
