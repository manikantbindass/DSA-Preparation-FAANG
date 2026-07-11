// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · Count the Number of Complete Components
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/count-the-number-of-complete-components/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We build an adjacency list for the graph. Then we perform DFS on each
//   unvisited node to find connected components. For each component, we
//   count the number of vertices (v) and the sum of degrees (which equals
//   twice the number of edges, 2e). A complete component must satisfy
//   v*(v-1) == 2e, i.e., the number of edges equals the maximum possible
//   edges in a complete graph of v vertices. We increment the answer for
//   each such component.
// 
// Complexity
//   Time  : O(n + m)
//   Space : O(n + m)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    
    public int countCompleteComponents(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                int[] t = dfs(i);
                if (t[0] * (t[0] - 1) == t[1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
    
    private int[] dfs(int i) {
        vis[i] = true;
        int x = 1, y = g[i].size();
        for (int j : g[i]) {
            if (!vis[j]) {
                int[] t = dfs(j);
                x += t[0];
                y += t[1];
            }
        }
        return new int[] {x, y};
    }
}
