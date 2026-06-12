/*
 * LeetCode Problem 3559: Number of Ways to Assign Edge Weights II
 * Problem Number: 3559
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-ii/
 * 
 * You are given a tree with n nodes numbered from 1 to n, and an array edges where edges[i] = [u_i, v_i].
 * You need to assign each edge a weight of 0 or 1. For multiple queries (u, v), find the number of ways
 * to assign weights such that the XOR distance between u and v is minimized.
 * 
 * Example:
 * Input: edges = [[1,2],[2,3],[2,4]], queries = [[1,3],[1,4]]
 * Output: [2,2]
 * 
 * Constraints:
 * - 1 <= n <= 10^5
 * - edges.length == n - 1
 * - 1 <= queries.length <= 10^5
 * 
 * Topics: Graph, Tree, LCA, Binary Lifting, Math
 * Time Complexity: O(n log n + q log n) - for preprocessing and answering queries
 * Space Complexity: O(n log n) - for the binary lifting table
 */

import java.util.*;

class Solution {
    static final int MOD = 1000000007;
    
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // Preprocess for LCA using binary lifting
        int log = 0;
        while ((1 << log) <= n) {
            log++;
        }
        
        int[][] up = new int[log][n + 1];
        int[] depth = new int[n + 1];
        
        // BFS to build parent and depth arrays
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        up[0][1] = 1;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph[u]) {
                if (v == up[0][u]) continue;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                queue.offer(v);
            }
        }
        
        // Build binary lifting table
        for (int j = 1; j < log; j++) {
            for (int i = 1; i <= n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
        
        // Find the maximum distance among all queries
        int maxDist = 0;
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            int w = lca(u, v, up, depth, log);
            int dist = depth[u] + depth[v] - 2 * depth[w];
            maxDist = Math.max(maxDist, dist);
        }
        
        // Precompute powers of 2 modulo MOD
        long[] pow2 = new long[maxDist + 1];
        pow2[0] = 1;
        for (int i = 1; i <= maxDist; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        // Answer each query
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int w = lca(u, v, up, depth, log);
            int dist = depth[u] + depth[v] - 2 * depth[w];
            
            if (dist == 0) {
                answer[i] = 0;
            } else {
                answer[i] = (int) pow2[dist - 1];
            }
        }
        
        return answer;
    }
    
    private int lca(int a, int b, int[][] up, int[] depth, int log) {
        // Ensure a is the deeper node
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        // Lift a to the same depth as b
        int diff = depth[a] - depth[b];
        for (int i = 0; i < log; i++) {
            if (((diff >> i) & 1) == 1) {
                a = up[i][a];
            }
        }
        
        if (a == b) return a;
        
        // Lift both nodes until their parents are the same
        for (int i = log - 1; i >= 0; i--) {
            if (up[i][a] != up[i][b]) {
                a = up[i][a];
                b = up[i][b];
            }
        }
        
        return up[0][a];
    }
}
