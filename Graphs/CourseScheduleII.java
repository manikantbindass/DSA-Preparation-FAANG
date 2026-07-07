// ──────────────────────────────────────────────────────────────────────
// LeetCode #210 · Course Schedule II
// Difficulty : Medium
// Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
// URL        : https://leetcode.com/problems/course-schedule-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   This problem is a classic topological sort on a directed graph. We
//   model courses as nodes and prerequisites as directed edges from
//   prerequisite to dependent course. Using Kahn's algorithm (BFS), we
//   compute indegrees for each node, then repeatedly enqueue nodes with
//   indegree 0, process them, and decrement indegrees of their neighbors.
//   The order of processing gives a valid course order. If the number of
//   processed nodes equals numCourses, we return the order; otherwise, a
//   cycle exists and we return an empty array.
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
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            graph[b].add(a);
            indegree[a]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[index++] = course;
            for (int neighbor : graph[course]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return index == numCourses ? order : new int[0];
    }
}
