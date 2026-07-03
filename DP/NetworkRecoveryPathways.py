# ──────────────────────────────────────────────────────────────────────
# LeetCode #3919 · Network Recovery Pathways
# Difficulty : Hard
# Topics     : Array, Binary Search, Dynamic Programming, Graph Theory, Topological Sort, Heap (Priority Queue), Shortest Path
# URL        : https://leetcode.com/problems/network-recovery-pathways/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We need to find the maximum possible minimum edge cost along any path
#   from node 0 to node n-1 that stays within total cost k and uses only
#   online intermediate nodes. Since the graph is a DAG, we can binary
#   search on the minimum edge cost threshold. For a candidate threshold
#   mid, we consider only edges with cost >= mid and run Dijkstra (or any
#   shortest path) to check if there exists a path from 0 to n-1 with
#   total cost <= k. The binary search finds the largest mid for which
#   such a path exists. If no path exists even with the smallest possible
#   threshold, return -1.
# 
# Complexity
#   Time  : O((n + m) log n log C) where C is the range of edge costs
#   Space : O(n + m)
# 
# Runtime  : 4 ms
# Memory   : 42.6 MB
# 
# Examples
#   Example 1:
#     Input  : edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
#     Output : 3
#   Example 2:
#     Input  : edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
#     Output : 6
# 
# Constraints
#   · n == online.length
#   · 2 <= n <= 5 * 104
#   · 0 <= m == edges.length <= min(105, n * (n - 1) / 2)
#   · edges[i] = [ui, vi, costi]
#   · 0 <= ui, vi < n
#   · ui != vi
#   · 0 <= costi <= 109
#   · 0 <= k <= 5 * 1013
#   · online[i] is either true or false, and both online[0] and online[n &minus; 1] are true.
#   · The given graph is a directed acyclic graph.
# ──────────────────────────────────────────────────────────────────────

import heapq
from typing import List

class Solution:
    def findMaxPathScore(self, edges: List[List[int]], online: List[bool], k: int) -> int:
        n = len(online)
        g = [[] for _ in range(n)]
        min_cost = float('inf')
        max_cost = 0
        for u, v, w in edges:
            if online[u] and online[v]:
                g[u].append((v, w))
                min_cost = min(min_cost, w)
                max_cost = max(max_cost, w)
        
        def check(mid: int) -> bool:
            dist = [float('inf')] * n
            dist[0] = 0
            pq = [(0, 0)]
            while pq:
                d, u = heapq.heappop(pq)
                if d > k:
                    return False
                if u == n - 1:
                    return True
                if dist[u] < d:
                    continue
                for v, w in g[u]:
                    if w < mid:
                        continue
                    nd = d + w
                    if nd < dist[v]:
                        dist[v] = nd
                        heapq.heappush(pq, (nd, v))
            return False
        
        if not check(min_cost):
            return -1
        lo, hi = min_cost, max_cost
        while lo < hi:
            mid = (lo + hi + 1) // 2
            if check(mid):
                lo = mid
            else:
                hi = mid - 1
        return lo
