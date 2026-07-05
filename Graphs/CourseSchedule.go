// ──────────────────────────────────────────────────────────────────────
// LeetCode #207 · Course Schedule
// Difficulty : Medium
// Topics     : Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
// URL        : https://leetcode.com/problems/course-schedule/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to determine if all courses can be finished given
//   prerequisites. This is equivalent to detecting a cycle in a directed
//   graph where courses are nodes and prerequisites are edges. We use
//   Kahn's algorithm (BFS-based topological sort). First, build an
//   adjacency list and compute in-degree for each node. Then, push all
//   nodes with in-degree 0 into a queue. While the queue is not empty, pop
//   a node, decrement in-degree of its neighbors, and if any neighbor's
//   in-degree becomes 0, push it. Count the number of nodes processed. If
//   the count equals numCourses, there is no cycle and we can finish all
//   courses; otherwise, there is a cycle and we return false.
// 
// Complexity
//   Time  : O(V + E) where V = numCourses, E = prerequisites.length
//   Space : O(V + E) for adjacency list and queue
// 
// Runtime  : 0 ms
// Memory   : 42.7 MB
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
    // Build adjacency list and in-degree array
    adj := make([][]int, numCourses)
    indegree := make([]int, numCourses)
    for _, prereq := range prerequisites {
        course := prereq[0]
        pre := prereq[1]
        adj[pre] = append(adj[pre], course)
        indegree[course]++
    }
    // Queue for BFS
    queue := make([]int, 0)
    for i := 0; i < numCourses; i++ {
        if indegree[i] == 0 {
            queue = append(queue, i)
        }
    }
    count := 0
    for len(queue) > 0 {
        node := queue[0]
        queue = queue[1:]
        count++
        for _, neighbor := range adj[node] {
            indegree[neighbor]--
            if indegree[neighbor] == 0 {
                queue = append(queue, neighbor)
            }
        }
    }
    return count == numCourses
}
