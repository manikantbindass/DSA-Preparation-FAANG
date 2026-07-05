// ──────────────────────────────────────────────────────────────────────
// LeetCode #207 · Course Schedule
// Difficulty : Medium
// Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
// URL        : https://leetcode.com/problems/course-schedule/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   This problem is essentially detecting a cycle in a directed graph. We
//   can use Kahn's algorithm (BFS-based topological sort) or DFS with
//   state marking. The BFS approach: build an adjacency list and indegree
//   array. Start with nodes having indegree 0, process them, decrement
//   indegrees of neighbors, and add new zero-indegree nodes. If we process
//   all nodes, no cycle exists. The DFS approach: mark nodes as
//   0=unvisited, 1=visiting, 2=visited; if we encounter a node in visiting
//   state, there's a cycle.
// 
// Complexity
//   Time  : O(V + E)
//   Space : O(V + E)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : numCourses = 2, prerequisites = [[1,0]]
//     Output : true
//     Explanation: There are a total of 2 courses to take.
//   Example 2:
//     Input  : numCourses = 2, prerequisites = [[1,0],[0,1]]
//     Output : false
//     Explanation: There are a total of 2 courses to take.
// 
// Constraints
//   · 1 <= numCourses <= 2000
//   · 0 <= prerequisites.length <= 5000
//   · prerequisites[i].length == 2
//   · 0 <= ai, bi < numCourses
//   · All the pairs prerequisites[i] are
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }
        // Queue for BFS (Kahn's algorithm)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int processed = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return processed == numCourses;
    }
}
