/*
 * LeetCode Problem 231: Power of Two
 * Problem Number: 231
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/power-of-two/
 *
 * Given an integer n, return true if it is a power of two. Otherwise, return
 * false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 *
 * Example 2:
 *
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 *
 * Example 3:
 *
 * Input: n = 3
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 	-231 <= n <= 231 - 1
 *
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 *
 * Example 1:
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 *
 * Example 2:
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 *
 * Example 3:
 * Input: n = 3
 * Output: false
 *
 * Constraints:
 * - 231 <= n <= 231 - 1
 *
 * Topics: Math, Bit Manipulation, Recursion
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stk =new ArrayDeque<>();
        while (root != null || !stk.isEmpty()) {
            if (root !=null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                if (--k== 0) {
                    return root.val;
                }
                root =root.right;
            }
        }
        return 0;
    }
}
