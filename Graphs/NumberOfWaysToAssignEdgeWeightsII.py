"""
LeetCode Problem 3559: Number of Ways to Assign Edge Weights II
Problem Number: 3559
Difficulty: Hard
Link: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-ii/

You are given a tree with n nodes numbered from 1 to n, and an array edges where edges[i] = [u_i, v_i].
You need to assign each edge a weight of 0 or 1. For multiple queries (u, v), find the number of ways
to assign weights such that the XOR distance between u and v is minimized.

Example:
Input: edges = [[1,2],[2,3],[2,4]], queries = [[1,3],[1,4]]
Output: [2,2]

Constraints:
- 1 <= n <= 10^5
- edges.length == n - 1
- 1 <= queries.length <= 10^5

Topics: Graph, Tree, LCA, Binary Lifting, Math
Time Complexity: O(n log n + q log n) - for preprocessing and answering queries
Space Complexity: O(n log n) - for the binary lifting table
"""

from collections import deque
from typing import List

class Solution:
    MOD = 10**9 + 7
    
    def assignEdgeWeights(self, edges: List[List[int]], queries: List[List[int]]) -> List[int]:
        n = len(edges) + 1
        graph = [[] for _ in range(n + 1)]
        
        # Build adjacency list
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
        
        # Preprocess for LCA using binary lifting
        log = 0
        while (1 << log) <= n:
            log += 1
        
        up = [[0] * (n + 1) for _ in range(log)]
        depth = [0] * (n + 1)
        
        # BFS to build parent and depth arrays
        queue = deque([1])
        up[0][1] = 1
        
        while queue:
            u = queue.popleft()
            for v in graph[u]:
                if v == up[0][u]:
                    continue
                depth[v] = depth[u] + 1
                up[0][v] = u
                queue.append(v)
        
        # Build binary lifting table
        for j in range(1, log):
            for i in range(1, n + 1):
                up[j][i] = up[j - 1][up[j - 1][i]]
        
        def lca(a: int, b: int) -> int:
            if depth[a] < depth[b]:
                a, b = b, a
            
            # Lift a to the same depth as b
            diff = depth[a] - depth[b]
            for i in range(log):
                if (diff >> i) & 1:
                    a = up[i][a]
            
            if a == b:
                return a
            
            # Lift both nodes until their parents are the same
            for i in range(log - 1, -1, -1):
                if up[i][a] != up[i][b]:
                    a = up[i][a]
                    b = up[i][b]
            
            return up[0][a]
        
        # Find the maximum distance among all queries
        max_dist = 0
        for u, v in queries:
            w = lca(u, v)
            dist = depth[u] + depth[v] - 2 * depth[w]
            max_dist = max(max_dist, dist)
        
        # Precompute powers of 2 modulo MOD
        pow2 = [1] * (max_dist + 1)
        for i in range(1, max_dist + 1):
            pow2[i] = (pow2[i - 1] * 2) % self.MOD
        
        # Answer each query
        answer = []
        for u, v in queries:
            w = lca(u, v)
            dist = depth[u] + depth[v] - 2 * depth[w]
            if dist == 0:
                answer.append(0)
            else:
                answer.append(pow2[dist - 1])
        
        return answer
