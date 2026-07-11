# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · Count the Number of Complete Components
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/count-the-number-of-complete-components/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We build an adjacency list for the graph. Then we perform DFS on each
#   unvisited node to find connected components. For each component, we
#   count the number of vertices (v) and the sum of degrees (which equals
#   twice the number of edges, 2e). A complete component must satisfy
#   v*(v-1) == 2e, i.e., the number of edges equals the maximum possible
#   edges in a complete graph of v vertices. We increment the answer for
#   each such component.
# 
# Complexity
#   Time  : O(n + m)
#   Space : O(n + m)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = 0
        
        def dfs(u: int) -> (int, int):
            vis[u] = True
            v_cnt = 1
            deg_sum = len(g[u])
            for w in g[u]:
                if not vis[w]:
                    sub_v, sub_deg = dfs(w)
                    v_cnt += sub_v
                    deg_sum += sub_deg
            return v_cnt, deg_sum
        
        for i in range(n):
            if not vis[i]:
                v, d = dfs(i)
                if v * (v - 1) == d:
                    ans += 1
        return ans
