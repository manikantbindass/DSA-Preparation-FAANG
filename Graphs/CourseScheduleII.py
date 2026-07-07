# ──────────────────────────────────────────────────────────────────────
# LeetCode #210 · Course Schedule II
# Difficulty : Medium
# Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
# URL        : https://leetcode.com/problems/course-schedule-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   This problem is a classic topological sort on a directed graph. We
#   model courses as nodes and prerequisites as directed edges from
#   prerequisite to dependent course. Using Kahn's algorithm (BFS), we
#   compute indegrees for each node, then repeatedly enqueue nodes with
#   indegree 0, process them, and decrement indegrees of their neighbors.
#   The order of processing gives a valid course order. If the number of
#   processed nodes equals numCourses, we return the order; otherwise, a
#   cycle exists and we return an empty array.
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
#     Output : [0,1]
#     Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
#   Example 2:
#     Input  : numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
#     Output : [0,2,1,3]
#     Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
#   Example 3:
#     Input  : numCourses = 1, prerequisites = []
#     Output : [0]
# 
# Constraints
#   · 1 <= numCourses <= 2000
#   · 0 <= prerequisites.length <= numCourses * (numCourses - 1)
#   · prerequisites[i].length == 2
#   · 0 <= ai, bi < numCourses
#   · ai != bi
#   · All the pairs [ai, bi] are
# ──────────────────────────────────────────────────────────────────────

from collections import deque
from typing import List

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        graph = [[] for _ in range(numCourses)]
        indegree = [0] * numCourses
        for a, b in prerequisites:
            graph[b].append(a)
            indegree[a] += 1
        queue = deque([i for i in range(numCourses) if indegree[i] == 0])
        order = []
        while queue:
            course = queue.popleft()
            order.append(course)
            for neighbor in graph[course]:
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0:
                    queue.append(neighbor)
        return order if len(order) == numCourses else []
