# LeetCode 108 - Convert Sorted Array to Binary Search Tree
# Time Complexity: O(n) | Space Complexity: O(log n)
from typing import List, Optional


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        def dfs(left: int, right: int) -> Optional[TreeNode]:
            if left > right:
                return None

            mid = (left + right) >> 1
            return TreeNode(nums[mid], dfs(left, mid - 1), dfs(mid + 1, right))

        return dfs(0, len(nums) - 1)
