/*
 * LeetCode Problem 310: Minimum Height Trees
 * Problem Number: 310
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-height-trees/
 *
 * A tree is an undirected graph in which any two vertices are connected by exactly
 * one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between the
 * two nodes ai and bi in the tree, you can choose any node of the tree as the
 * root. When you select a node x as the root, the result tree has height h. Among
 * all possible rooted trees, those with minimum height (i.e. min(h))  are called
 * minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path
 * between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node
 * with label 1 which is the only MHT.
 *
 * Example 2:
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 2 * 104
 * 	edges.length == n - 1
 * 	0 <= ai, bi < n
 * 	ai != bi
 * 	All the pairs (ai, bi) are distinct.
 * 	The given input is guaranteed to be a tree and there will be no repeated edges.
 *
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Example 2:
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 * Constraints:
 * - 1 <= n <= 2 * 104
 * - edges.length == n - 1
 * - 0 <= ai, bi < n
 * - ai != bi
 * - All the pairs (ai, bi) are distinct.
 * - The given input is
 *
 * Topics: Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.7 MB
 */

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return List.of(0);
        }
        List<Integer>[] g=new List[n];
        Arrays.setAll(g, k-> new ArrayList<>());
        int[] degree=new int[n];
        for (int[] e :edges) {
            int a = e[0], b=e[1];
            g[a].add(b);
            g[b].add(a);
            ++degree[a];
            ++degree[b];
        }
        Deque<Integer> q=new ArrayDeque<>();
        for (int i =0; i < n; ++i) {
            if (degree[i]== 1) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.clear();
            for (int i= q.size(); i > 0; --i) {
                int a=q.poll();
                ans.add(a);
                for (int b :g[a]) {
                    if (--degree[b]==1) {
                        q.offer(b);
                    }
                }
            }
        }
        return ans;
    }
}
