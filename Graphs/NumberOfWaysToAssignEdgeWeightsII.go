/*
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
*/

package graphs

const mod = 1000000007

func assignEdgeWeights(edges [][]int, queries [][]int) []int {
    n := len(edges) + 1
    graph := make([][]int, n+1)
    for i := 1; i <= n; i++ {
        graph[i] = make([]int, 0)
    }
    
    // Build adjacency list
    for _, edge := range edges {
        u, v := edge[0], edge[1]
        graph[u] = append(graph[u], v)
        graph[v] = append(graph[v], u)
    }
    
    // Preprocess for LCA using binary lifting
    log := 0
    for (1 << log) <= n {
        log++
    }
    
    up := make([][]int, log)
    for i := 0; i < log; i++ {
        up[i] = make([]int, n+1)
    }
    depth := make([]int, n+1)
    
    // BFS to build parent and depth arrays
    queue := []int{1}
    up[0][1] = 1
    
    for len(queue) > 0 {
        u := queue[0]
        queue = queue[1:]
        for _, v := range graph[u] {
            if v == up[0][u] {
                continue
            }
            depth[v] = depth[u] + 1
            up[0][v] = u
            queue = append(queue, v)
        }
    }
    
    // Build binary lifting table
    for j := 1; j < log; j++ {
        for i := 1; i <= n; i++ {
            up[j][i] = up[j-1][up[j-1][i]]
        }
    }
    
    lca := func(a, b int) int {
        if depth[a] < depth[b] {
            a, b = b, a
        }
        
        // Lift a to the same depth as b
        diff := depth[a] - depth[b]
        for i := 0; i < log; i++ {
            if (diff>>i)&1 == 1 {
                a = up[i][a]
            }
        }
        
        if a == b {
            return a
        }
        
        // Lift both nodes until their parents are the same
        for i := log - 1; i >= 0; i-- {
            if up[i][a] != up[i][b] {
                a = up[i][a]
                b = up[i][b]
            }
        }
        
        return up[0][a]
    }
    
    // Find the maximum distance among all queries
    maxDist := 0
    for _, query := range queries {
        u, v := query[0], query[1]
        w := lca(u, v)
        dist := depth[u] + depth[v] - 2*depth[w]
        if dist > maxDist {
            maxDist = dist
        }
    }
    
    // Precompute powers of 2 modulo MOD
    pow2 := make([]int, maxDist+1)
    pow2[0] = 1
    for i := 1; i <= maxDist; i++ {
        pow2[i] = (pow2[i-1] * 2) % mod
    }
    
    // Answer each query
    answer := make([]int, len(queries))
    for i, query := range queries {
        u, v := query[0], query[1]
        w := lca(u, v)
        dist := depth[u] + depth[v] - 2*depth[w]
        if dist == 0 {
            answer[i] = 0
        } else {
            answer[i] = pow2[dist-1]
        }
    }
    
    return answer
}
