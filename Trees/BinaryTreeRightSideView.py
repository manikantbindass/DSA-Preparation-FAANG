# ──────────────────────────────────────────────────────────────────────
# LeetCode #199 · Binary Tree Right Side View
# Difficulty : Medium
# Topics     : Tree, Depth-First Search, Breadth-First Search, Binary Tree
# URL        : https://leetcode.com/problems/binary-tree-right-side-view/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We perform a level-order traversal (BFS) of the binary tree. For each
#   level, we add the value of the first node in the queue (which is the
#   rightmost node because we push right child before left child) to the
#   result list. Then we process all nodes of the current level by polling
#   them and enqueuing their right and left children (in that order) for
#   the next level. This ensures that at each level, the first node we see
#   is the rightmost node.
# 
# Complexity
#   Time  : O(n)
#   Space : O(n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : root = [1,2,3,null,5,null,4]
#     Output : [1,3,4]
#   Example 2:
#     Input  : root = [1,2,3,4,null,null,null,5]
#     Output : [1,3,4,5]
#   Example 3:
#     Input  : root = [1,null,3]
#     Output : [1,3]
#   Example 4:
#     Input  : root = []
#     Output : []
# 
# Constraints
#   · The number of nodes in the tree is in the range [0, 100].
#   · -100 <= Node.val <= 100
# ──────────────────────────────────────────────────────────────────────

from collections import deque
from typing import List, Optional

class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []
        result = []
        queue = deque([root])
        while queue:
            level_size = len(queue)
            for i in range(level_size):
                node = queue.popleft()
                if i == 0:
                    result.append(node.val)
                if node.right:
                    queue.append(node.right)
                if node.left:
                    queue.append(node.left)
        return result
