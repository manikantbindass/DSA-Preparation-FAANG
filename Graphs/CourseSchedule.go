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

func canFinish(numCourses int, prerequisites [][]int) bool {
    // Build adjacency list and indegree array
    adj := make([][]int, numCourses)
    indegree := make([]int, numCourses)
    for _, pre := range prerequisites {
        course := pre[0]
        prereq := pre[1]
        adj[prereq] = append(adj[prereq], course)
        indegree[course]++
    }
    // Queue for BFS (Kahn's algorithm)
    queue := make([]int, 0)
    for i := 0; i < numCourses; i++ {
        if indegree[i] == 0 {
            queue = append(queue, i)
        }
    }
    processed := 0
    for len(queue) > 0 {
        node := queue[0]
        queue = queue[1:]
        processed++
        for _, neighbor := range adj[node] {
            indegree[neighbor]--
            if indegree[neighbor] == 0 {
                queue = append(queue, neighbor)
            }
        }
    }
    return processed == numCourses
}
