/*
 * LeetCode Problem 133: Clone Graph
 * Problem Number: 133
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/clone-graph/
 * 
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * 
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * 
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * 
 * Example 3:
 * Input: adjList = []
 * Output: []
 * 
 * Constraints:
 * - The number of nodes in the graph is in the range [0, 100].
 * - 1 <= Node.val <= 100
 * - Node.val is unique for each node.
 * - There are no multiple edges and no self-loops in the graph.
 * - The graph is connected.
 * 
 * Topics: Hash Table, Depth-First Search, Breadth-First Search, Graph
 * Time Complexity: O(N + E) - where N is number of nodes and E is number of edges
 * Space Complexity: O(N) - for the visited map and recursion stack
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    private Map<Node, Node> visited = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node);
    }
    
    private Node dfs(Node node) {
        // If node already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create clone of current node
        Node clone = new Node(node.val);
        visited.put(node, clone);
        
        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor));
        }
        
        return clone;
    }
}
