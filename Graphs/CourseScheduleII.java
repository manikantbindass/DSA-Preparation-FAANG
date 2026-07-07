// ──────────────────────────────────────────────────────────────────────
// LeetCode #210 · Course Schedule II
// Difficulty : Medium
// Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
// URL        : https://leetcode.com/problems/course-schedule-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   This problem is a classic topological sort on a directed graph where
//   courses are nodes and prerequisites are edges from prerequisite to
//   dependent course. We use Kahn's algorithm (BFS) to compute a valid
//   ordering. First, build an adjacency list and compute indegree for each
//   node. Initialize a queue with all nodes having indegree 0. While the
//   queue is not empty, pop a node, add it to the result, and for each
//   neighbor, decrement its indegree; if it becomes 0, push it into the
//   queue. After processing, if the result size equals numCourses, return
//   the result; otherwise, return an empty array indicating a cycle.
// 
// Complexity
//   Time  : O(V + E) where V = numCourses, E = prerequisites.length
//   Space : O(V + E) for adjacency list and queue
// 
// Runtime  : 0 ms
// Memory   : 42.9 MB
// 
// Examples
//   Example 1:
//     Input  : numCourses = 2, prerequisites = [[1,0]]
//     Output : [0,1]
//     Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//   Example 2:
//     Input  : numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//     Output : [0,2,1,3]
//     Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//   Example 3:
//     Input  : numCourses = 1, prerequisites = []
//     Output : [0]
// 
// Constraints
//   · 1 <= numCourses <= 2000
//   · 0 <= prerequisites.length <= numCourses * (numCourses - 1)
//   · prerequisites[i].length == 2
//   · 0 <= ai, bi < numCourses
//   · ai != bi
//   · All the pairs [ai, bi] are
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            g[b].add(a);
            indeg[a]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) q.offer(i);
        }
        int[] ans = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            ans[idx++] = u;
            for (int v : g[u]) {
                if (--indeg[v] == 0) q.offer(v);
            }
        }
        return idx == numCourses ? ans : new int[0];
    }
}
