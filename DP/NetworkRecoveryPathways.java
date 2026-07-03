// ──────────────────────────────────────────────────────────────────────
// LeetCode #3919 · Network Recovery Pathways
// Difficulty : Hard
// Topics     : Array, Binary Search, Dynamic Programming, Graph Theory, Topological Sort, Heap (Priority Queue), Shortest Path
// URL        : https://leetcode.com/problems/network-recovery-pathways/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the maximum possible minimum edge cost along any path
//   from node 0 to node n-1 that stays within total cost k and only uses
//   online intermediate nodes. Since the graph is a DAG, we can binary
//   search on the minimum edge cost threshold. For a candidate threshold
//   mid, we consider only edges with cost >= mid and run Dijkstra (or any
//   shortest path) to check if there exists a path from 0 to n-1 with
//   total cost <= k. The binary search finds the largest mid for which
//   such a path exists. If no path exists even for the smallest possible
//   threshold, return -1.
// 
// Complexity
//   Time  : O((n + m) log n log C) where C is the range of edge costs
//   Space : O(n + m)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
//     Output : 3
//   Example 2:
//     Input  : edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
//     Output : 6
// 
// Constraints
//   · n == online.length
//   · 2 <= n <= 5 * 104
//   · 0 <= m == edges.length <= min(105, n * (n - 1) / 2)
//   · edges[i] = [ui, vi, costi]
//   · 0 <= ui, vi < n
//   · ui != vi
//   · 0 <= costi <= 109
//   · 0 <= k <= 5 * 1013
//   · online[i] is either true or false, and both online[0] and online[n &minus; 1] are true.
//   · The given graph is a directed acyclic graph.
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    int n;
    List<int[]>[] g;
    long k;
    
    boolean check(int mid) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 4);
        dist[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d > k) return false;
            if (u == n - 1) return true;
            if (dist[u] < d) continue;
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (w < mid) continue;
                long nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }
        return false;
    }
    
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.k = k;
        n = online.length;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        int l = Integer.MAX_VALUE;
        int r = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (!online[u] || !online[v]) continue;
            g[u].add(new int[]{v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }
        return check(l) ? l : -1;
    }
}
