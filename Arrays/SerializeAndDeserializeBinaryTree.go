/*
 * LeetCode Problem 297: Serialize and Deserialize Binary Tree
 * Problem Number: 297
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work. You
 * just need to ensure that a binary tree can be serialized to a string and this
 * string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the tree is in the range [0, 104].
 * 	-1000 <= Node.val <= 1000
 *
 * Example 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 104].
 * - -1000 <= Node.val <= 1000
 *
 * Topics: String, Tree, Depth-First Search, Breadth-First Search, Design, Binary Tree
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 24 ms
 * Memory: 48.5 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * /**
 *  * Definition for a binary tree node.
 *  * public class TreeNode {
 *  *     int val;
 *  *     TreeNode left;
 *  *     TreeNode right;
 *  *     TreeNode(int x) { val = x; }
 *  * }
 *  */
 * public class Codec {
 *     // Encodes a tree to a single string.
 *     public String serialize(TreeNode root) {
 *         if (root == null) {
 *             return null;
 *         }
 *         List<String> ans = new ArrayList<>();
 *         Deque<TreeNode> q = new LinkedList<>();
 *         q.offer(root);
 *         while (!q.isEmpty()) {
 *             TreeNode node = q.poll();
 *             if (node != null) {
 *                 ans.add(node.val + "");
 *                 q.offer(node.left);
 *                 q.offer(node.right);
 *             } else {
 *                 ans.add("#");
 *             }
 *         }
 *         return String.join(",", ans);
 *     }
 *     // Decodes your encoded data to tree.
 *     public TreeNode deserialize(String data) {
 *         if (data == null) {
 *             return null;
 *         }
 *         String[] vals = data.split(",");
 *         int i = 0;
 *         TreeNode root = new TreeNode(Integer.valueOf(vals[i++]));
 *         Deque<TreeNode> q = new ArrayDeque<>();
 *         q.offer(root);
 *         while (!q.isEmpty()) {
 *             TreeNode node = q.poll();
 *             if (!"#".equals(vals[i])) {
 *                 node.left = new TreeNode(Integer.valueOf(vals[i]));
 *                 q.offer(node.left);
 *             }
 *             ++i;
 *             if (!"#".equals(vals[i])) {
 *                 node.right = new TreeNode(Integer.valueOf(vals[i]));
 *                 q.offer(node.right);
 *             }
 *             ++i;
 *         }
 *         return root;
 *     }
 * }
 * // Your Codec object will be instantiated and called as such:
 * // Codec codec = new Codec();
 * // codec.deserialize(codec.serialize(root));
 */

package serializeanddeserializebinarytree

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
