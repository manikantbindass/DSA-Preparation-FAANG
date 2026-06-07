/*
LeetCode Problem 133: Clone Graph
Problem Number: 133
Difficulty: Medium
Link: https://leetcode.com/problems/clone-graph/

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]

Example 2:
Input: adjList = [[]]
Output: [[]]

Example 3:
Input: adjList = []
Output: []

Constraints:
- The number of nodes in the graph is in the range [0, 100].
- 1 <= Node.val <= 100
- Node.val is unique for each node.
- There are no multiple edges and no self-loops in the graph.
- The graph is connected.

Topics: Hash Table, Depth-First Search, Breadth-First Search, Graph
Time Complexity: O(N + E) - where N is number of nodes and E is number of edges
Space Complexity: O(N) - for the visited map and recursion stack
*/

package graphs

// Node defines a node in a graph.
type Node struct {
    Val       int
    Neighbors []*Node
}

func cloneGraph(node *Node) *Node {
    if node == nil {
        return nil
    }
    
    visited := make(map[*Node]*Node)
    
    var dfs func(curr *Node) *Node
    dfs = func(curr *Node) *Node {
        // If node already cloned, return the clone
        if clone, exists := visited[curr]; exists {
            return clone
        }
        
        // Create clone of current node
        clone := &Node{Val: curr.Val, Neighbors: make([]*Node, 0)}
        visited[curr] = clone
        
        // Recursively clone all neighbors
        for _, neighbor := range curr.Neighbors {
            clone.Neighbors = append(clone.Neighbors, dfs(neighbor))
        }
        
        return clone
    }
    
    return dfs(node)
}
