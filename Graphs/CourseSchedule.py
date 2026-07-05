# ──────────────────────────────────────────────────────────────────────
# LeetCode #207 · Course Schedule
# Difficulty : Medium
# Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
# URL        : https://leetcode.com/problems/course-schedule/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   This problem is essentially detecting a cycle in a directed graph. We
#   can use Kahn's algorithm (BFS-based topological sort) or DFS with
#   state marking. The BFS approach: build an adjacency list and indegree
#   array. Start with nodes having indegree 0, process them, decrement
#   indegrees of neighbors, and add new zero-indegree nodes. If we process
#   all nodes, no cycle exists. The DFS approach: mark nodes as
#   0=unvisited, 1=visiting, 2=visited; if we encounter a node in visiting
#   state, there's a cycle.
# 
# Complexity
#   Time  : O(V + E)
#   Space : O(V + E)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : numCourses = 2, prerequisites = [[1,0]]
#     Output : true
#     Explanation: There are a total of 2 courses to take.
#   Example 2:
#     Input  : numCourses = 2, prerequisites = [[1,0],[0,1]]
#     Output : false
#     Explanation: There are a total of 2 courses to take.
# 
# Constraints
#   · 1 <= numCourses <= 2000
#   · 0 <= prerequisites.length <= 5000
#   · prerequisites[i].length == 2
#   · 0 <= ai, bi < numCourses
#   · All the pairs prerequisites[i] are
# ──────────────────────────────────────────────────────────────────────

from collections import deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        # Build adjacency list and indegree array
        adj = [[] for _ in range(numCourses)]
        indegree = [0] * numCourses
        for course, prereq in prerequisites:
            adj[prereq].append(course)
            indegree[course] += 1
        # Queue for BFS (Kahn's algorithm)
        queue = deque([i for i in range(numCourses) if indegree[i] == 0])
        processed = 0
        while queue:
            node = queue.popleft()
            processed += 1
            for neighbor in adj[node]:
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0:
                    queue.append(neighbor)
        return processed == numCourses
