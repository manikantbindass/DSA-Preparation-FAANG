/*
 * LeetCode Problem 3576: Find Subtree Sizes After Changes
 * Problem Number: 3576
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/
 *
 * You are given a tree rooted at node 0 that consists of n nodes numbered from 0
 * to n - 1. The tree is represented by an array parent of size n, where parent[i]
 * is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned
 * to node i.
 *
 * We make the following changes on the tree one time simultaneously for all nodes
 * x from 1 to n - 1:
 *
 * 	Find the closest node y to node x such that y is an ancestor of x, and s[x] ==
 * s[y].
 * 	If node y does not exist, do nothing.
 * 	Otherwise, remove the edge between x and its current parent and make node y the
 * new parent of x by adding an edge between them.
 *
 * Return an array answer of size n where answer[i] is the size of the subtree
 * rooted at node i in the final tree.
 *
 *
 *
 * Example 1:
 *
 * Input: parent = [-1,0,0,1,1,1], s = "abaabc"
 *
 * Output: [6,3,1,1,1,1]
 *
 * Explanation:
 *
 * The parent of node 3 will change from node 1 to node 0.
 *
 * Example 2:
 *
 * Input: parent = [-1,0,4,0,1], s = "abbba"
 *
 * Output: [5,2,1,1,1]
 *
 * Explanation:
 *
 * The following changes will happen at the same time:
 *
 * 	The parent of node 4 will change from node 1 to node 0.
 * 	The parent of node 2 will change from node 4 to node 1.
 *
 *
 *
 * Constraints:
 *
 * 	n == parent.length == s.length
 * 	1 <= n <= 105
 * 	0 <= parent[i] <= n - 1 for all i >= 1.
 * 	parent[0] == -1
 * 	parent represents a valid tree.
 * 	s consists only of lowercase English letters.
 *
 * Example 1:
 * Input: parent = [-1,0,0,1,1,1], s = "abaabc"
 * Output: [6,3,1,1,1,1]
 *
 * Example 2:
 * Input: parent = [-1,0,4,0,1], s = "abbba"
 * Output: [5,2,1,1,1]
 *
 * Constraints:
 * - n == parent.length == s.length
 * - 1 <= n <= 105
 * - 0 <= parent[i] <= n - 1 for all i >= 1.
 * - parent[0] == -1
 * - parent represents a valid tree.
 * - s consists only of lowercase English letters.
 *
 * Topics: Array, Hash Table, String, Tree, Depth-First Search
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * Runtime: 1 ms
 * Memory: 42.1 MB
 */

class Solution {
    private List<Integer>[] g;
    private List<Integer>[] d;
    private char[] s;
    private int[] ans;
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        g = new List[n];
        d = new List[26];
        this.s = s.toCharArray();
        Arrays.setAll(g, k -> new ArrayList<>());
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        ans = new int[n];
        dfs(0, -1);
        return ans;
    }
    private void dfs(int i, int fa) {
        ans[i] = 1;
        int idx = s[i] - 'a';
        d[idx].add(i);
        for (int j : g[i]) {
            dfs(j, i);
        }
        int k = d[idx].size() > 1 ? d[idx].get(d[idx].size() - 2) : fa;
        if (k >= 0) {
            ans[k] += ans[i];
        }
        d[idx].remove(d[idx].size() - 1);
    }
}
