# ──────────────────────────────────────────────────────────────────────
# LeetCode #2582 · Minimum Score of a Path Between Two Cities
# Difficulty : Medium
# Topics     : Depth-First Search, Breadth-First Search, Union-Find, Graph Theory
# URL        : https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem asks for the minimum possible score of a path between city
#   1 and city n, where the score of a path is the minimum edge weight
#   along that path. Since we can revisit cities and edges, the optimal
#   strategy is to find the minimum edge weight in the entire connected
#   component that contains both city 1 and city n. This is because any
#   edge in that component can be included in a path by traversing back
#   and forth. Therefore, we can perform a DFS (or BFS) starting from city
#   1, visiting all reachable cities, and tracking the minimum edge weight
#   encountered. The answer is that minimum weight. The solution uses an
#   adjacency list representation of the graph and a recursive DFS.
# 
# Complexity
#   Time  : O(n + m)
#   Space : O(n + m)
# 
# Runtime  : 1 ms
# Memory   : 42.7 MB
# 
# Examples
#   Example 1:
#     Input  : n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
#     Output : 5
#     Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
#   Example 2:
#     Input  : n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
#     Output : 2
#     Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
# 
# Constraints
#   · 2 <= n <= 105
#   · 1 <= roads.length <= 105
#   · roads[i].length == 3
#   · 1 <= ai, bi <= n
#   · ai != bi
#   · 1 <= distancei <= 104
#   · There are no repeated edges.
#   · There is at least one path between 1 and n.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = float('inf')
        vis = [False] * (n + 1)
        
        def dfs(u: int) -> None:
            nonlocal ans
            vis[u] = True
            for v, w in g[u]:
                ans = min(ans, w)
                if not vis[v]:
                    dfs(v)
        
        dfs(1)
        return ans
