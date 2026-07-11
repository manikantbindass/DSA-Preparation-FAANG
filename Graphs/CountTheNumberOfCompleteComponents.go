// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · Count the Number of Complete Components
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/count-the-number-of-complete-components/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We build an adjacency list for the graph. Then we perform DFS on each
//   unvisited node to find connected components. For each component, we
//   count the number of vertices (v) and the sum of degrees (which equals
//   twice the number of edges, 2e). A complete component must satisfy
//   v*(v-1) == 2e, i.e., the number of edges equals the maximum possible
//   edges in a complete graph of v vertices. We increment the answer for
//   each such component.
// 
// Complexity
//   Time  : O(n + m)
//   Space : O(n + m)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func countCompleteComponents(n int, edges [][]int) int {
    g := make([][]int, n)
    for i := 0; i < n; i++ {
        g[i] = []int{}
    }
    for _, e := range edges {
        a, b := e[0], e[1]
        g[a] = append(g[a], b)
        g[b] = append(g[b], a)
    }
    vis := make([]bool, n)
    ans := 0
    
    var dfs func(u int) (int, int)
    dfs = func(u int) (int, int) {
        vis[u] = true
        vCnt := 1
        degSum := len(g[u])
        for _, w := range g[u] {
            if !vis[w] {
                subV, subDeg := dfs(w)
                vCnt += subV
                degSum += subDeg
            }
        }
        return vCnt, degSum
    }
    
    for i := 0; i < n; i++ {
        if !vis[i] {
            v, d := dfs(i)
            if v*(v-1) == d {
                ans++
            }
        }
    }
    return ans
}
