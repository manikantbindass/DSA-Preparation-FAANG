// LeetCode 106 - Construct Binary Tree from Inorder and Postorder Traversal
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func buildTree(inorder []int, postorder []int) *TreeNode {
	inorderIndex := make(map[int]int, len(inorder))

	for index, value := range inorder {
		inorderIndex[value] = index
	}

	var dfs func(int, int, int) *TreeNode
	dfs = func(inorderStart int, postorderStart int, size int) *TreeNode {
		if size <= 0 {
			return nil
		}

		rootValue := postorder[postorderStart+size-1]
		splitIndex := inorderIndex[rootValue]
		left := dfs(inorderStart, postorderStart, splitIndex-inorderStart)
		right := dfs(
			splitIndex+1,
			postorderStart+splitIndex-inorderStart,
			size-splitIndex+inorderStart-1,
		)

		return &TreeNode{Val: rootValue, Left: left, Right: right}
	}

	return dfs(0, 0, len(inorder))
}
