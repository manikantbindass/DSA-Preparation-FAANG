// ──────────────────────────────────────────────────────────────────────
// LeetCode #199 · Binary Tree Right Side View
// Difficulty : Medium
// Topics     : Tree, Depth-First Search, Breadth-First Search, Binary Tree
// URL        : https://leetcode.com/problems/binary-tree-right-side-view/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We perform a level-order traversal (BFS) of the binary tree. For each
//   level, we add the value of the first node in the queue (which is the
//   rightmost node because we push right child before left child) to the
//   result list. Then we process all nodes of the current level by polling
//   them and enqueuing their right and left children (in that order) for
//   the next level. This ensures that at each level, the first node we see
//   is the rightmost node.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : root = [1,2,3,null,5,null,4]
//     Output : [1,3,4]
//   Example 2:
//     Input  : root = [1,2,3,4,null,null,null,5]
//     Output : [1,3,4,5]
//   Example 3:
//     Input  : root = [1,null,3]
//     Output : [1,3]
//   Example 4:
//     Input  : root = []
//     Output : []
// 
// Constraints
//   · The number of nodes in the tree is in the range [0, 100].
//   · -100 <= Node.val <= 100
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result.add(node.val); // first node of level is rightmost
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
        }
        return result;
    }
}
