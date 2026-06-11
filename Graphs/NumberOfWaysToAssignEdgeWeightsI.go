/*
LeetCode Problem 3558: Number of Ways to Assign Edge Weights I
Problem Number: 3558
Difficulty: Hard
Link: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/

You are given a tree with n nodes numbered from 1 to n, and an array edges where edges[i] = [u_i, v_i].
You need to assign each edge a weight of 0 or 1. The value of the tree is defined as the sum of the
distances between all pairs of nodes, where the distance between two nodes is the XOR of the weights
along the unique path between them.

Return the number of ways to assign weights such that the value of the tree is minimized.

Example:
Input: edges = [[1,2],[2,3],[2,4]]
Output: 2

Constraints:
- 1 <= n <= 10^5
- edges.length == n - 1

Topics: Graph, Tree, DFS, Math
Time Complexity: O(n) - single DFS to find diameter
Space Complexity: O(n) - for adjacency list and recursion stack
*/

package graphs

const mod int = 1000000007

func assignEdgeWeights(edges [][]int) int {
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
    
    // DFS function returns (farthestNode, maxDepth)
    var dfs func(node, parent, depth int) (int, int)
    dfs = func(node, parent, depth int) (int, int) {
        farthestNode := node
        maxDepth := depth
        
        for _, neighbor := range graph[node] {
            if neighbor != parent {
                childNode, childDepth := dfs(neighbor, node, depth+1)
                if childDepth > maxDepth {
                    maxDepth = childDepth
                    farthestNode = childNode
                }
            }
        }
        
        return farthestNode, maxDepth
    }
    
    // First DFS to find farthest node from arbitrary node (1)
    farthest, _ := dfs(1, -1, 0)
    // Second DFS to find farthest distance from that node
    _, diameter := dfs(farthest, -1, 0)
    
    // Number of ways is 2^(diameter - 1) mod MOD
    return powMod(2, diameter-1)
}

func powMod(a, b int) int {
    result := 1
    for b > 0 {
        if b&1 == 1 {
            result = result * a % mod
        }
        a = a * a % mod
        b >>= 1
    }
    return result
}
