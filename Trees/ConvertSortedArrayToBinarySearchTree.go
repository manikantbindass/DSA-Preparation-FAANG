// LeetCode 108 - Convert Sorted Array to Binary Search Tree
// Time Complexity: O(n) | Space Complexity: O(log n)
package main

func sortedArrayToBST(nums []int) *TreeNode {
	var dfs func(int, int) *TreeNode
	dfs = func(left int, right int) *TreeNode {
		if left > right {
			return nil
		}

		mid := (left + right) >> 1
		return &TreeNode{
			Val:   nums[mid],
			Left:  dfs(left, mid-1),
			Right: dfs(mid+1, right),
		}
	}

	return dfs(0, len(nums)-1)
}
