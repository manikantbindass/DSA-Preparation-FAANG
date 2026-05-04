# LeetCode 106 - Construct Binary Tree from Inorder and Postorder Traversal
# Time Complexity: O(n) | Space Complexity: O(n)
from typing import List, Optional


class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        inorder_index = {value: index for index, value in enumerate(inorder)}

        def dfs(inorder_start: int, postorder_start: int, size: int) -> Optional[TreeNode]:
            if size <= 0:
                return None

            root_value = postorder[postorder_start + size - 1]
            split_index = inorder_index[root_value]
            left = dfs(inorder_start, postorder_start, split_index - inorder_start)
            right = dfs(
                split_index + 1,
                postorder_start + split_index - inorder_start,
                size - split_index + inorder_start - 1,
            )
            return TreeNode(root_value, left, right)

        return dfs(0, 0, len(inorder))
