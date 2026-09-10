/*
 * LeetCode Problem 2347: Count Nodes Equal to Average of Subtree
 * Problem Number: 2347
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 *
 * Given the root of a binary tree, return the number of nodes where the value of
 * the node is equal to the average of the values in its subtree.
 *
 * Note:
 *
 * 	The average of n elements is the sum of the n elements divided by n and rounded
 * down to the nearest integer.
 * 	A subtree of root is a tree consisting of root and all of its descendants.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 * Explanation: 
 * For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6)
 * / 6 = 24 / 6 = 4.
 * For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 =
 * 5.
 * For the node with value 0: The average of its subtree is 0 / 1 = 0.
 * For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * For the node with value 6: The average of its subtree is 6 / 1 = 6.
 *
 * Example 2:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 *
 *
 *
 * Constraints:
 *
 * 	The number of nodes in the tree is in the range [1, 1000].
 * 	0 <= Node.val <= 1000
 *
 * Example 1:
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 *
 * Example 2:
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - 0 <= Node.val <= 1000
 *
 * Topics: Tree, Depth-First Search, Binary Tree
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.5 MB
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
 *  *     TreeNode() {}
 *  *     TreeNode(int val) { this.val = val; }
 *  *     TreeNode(int val, TreeNode left, TreeNode right) {
 *  *         this.val = val;
 *  *         this.left = left;
 *  *         this.right = right;
 *  *     }
 *  * }
 *  */
 * class Solution {
 *     private int ans;
 *     public int averageOfSubtree(TreeNode root) {
 *         dfs(root);
 *         return ans;
 *     }
 *     private int[] dfs(TreeNode root) {
 *         if (root== null) {
 *             return new int[2];
 *         }
 *         var l= dfs(root.left);
 *         var r = dfs(root.right);
 *         int s=l[0]+r[0] + root.val;
 *         int n= l[1] +r[1]+ 1;
 *         if (s/n==root.val) {
 *             ++ans;
 *         }
 *         return new int[] {s,n};
 *     }
 * }
 */

package countnodesequaltoaverageofsubtree

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
