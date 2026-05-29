/*
LeetCode Problem 109: Convert Sorted List to Binary Search Tree
Problem Number: 109
Difficulty: Medium
Link: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

Given the head of a singly linked list where elements are sorted in ascending order,
convert it into a height-balanced binary search tree.

Example 1:
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5] which represents the height-balanced BST.

Example 2:
Input: head = []
Output: []

Constraints:
- The number of nodes in the list is in the range [0, 2 * 10^4].
- -10^5 <= Node.val <= 10^5

Topics: Linked List, Divide and Conquer, Tree, Binary Search Tree, Binary Tree
Time Complexity: O(n) - building the list and constructing tree
Space Complexity: O(n) - for the list conversion and recursion stack
*/

package trees

// ListNode defines a node in a singly-linked list.
type ListNode struct {
    Val  int
    Next *ListNode
}

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func sortedListToBST(head *ListNode) *TreeNode {
    // Convert linked list to slice for O(1) access
    values := make([]int, 0)
    for curr := head; curr != nil; curr = curr.Next {
        values = append(values, curr.Val)
    }
    
    var buildBST func(left, right int) *TreeNode
    buildBST = func(left, right int) *TreeNode {
        if left > right {
            return nil
        }
        // Choose middle element as root to maintain balance
        mid := (left + right) / 2
        root := &TreeNode{Val: values[mid]}
        root.Left = buildBST(left, mid-1)
        root.Right = buildBST(mid+1, right)
        return root
    }
    
    return buildBST(0, len(values)-1)
}
