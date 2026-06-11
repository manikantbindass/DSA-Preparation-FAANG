/*
 * LeetCode Problem 3558: Number of Ways to Assign Edge Weights I
 * Problem Number: 3558
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/
 * 
 * You are given a tree with n nodes numbered from 1 to n, and an array edges where edges[i] = [u_i, v_i].
 * You need to assign each edge a weight of 0 or 1. The value of the tree is defined as the sum of the
 * distances between all pairs of nodes, where the distance between two nodes is the XOR of the weights
 * along the unique path between them.
 * 
 * Return the number of ways to assign weights such that the value of the tree is minimized.
 * 
 * Example:
 * Input: edges = [[1,2],[2,3],[2,4]]
 * Output: 2
 * 
 * Constraints:
 * - 1 <= n <= 10^5
 * - edges.length == n - 1
 * 
 * Topics: Graph, Tree, DFS, Math
 * Time Complexity: O(n) - single DFS to find diameter
 * Space Complexity: O(n) - for adjacency list and recursion stack
 */

import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private static final int MOD = 1_000_000_007;
    
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // Find the diameter (longest path) of the tree
        int diameter = findDiameter(n);
        
        // Number of ways is 2^(diameter - 1) mod MOD
        return (int) pow(2, diameter - 1, MOD);
    }
    
    private int findDiameter(int n) {
        // First DFS to find farthest node from arbitrary node (1)
        int farthestNode = dfs(1, -1, 0)[0];
        // Second DFS to find farthest distance from that node
        int[] result = dfs(farthestNode, -1, 0);
        return result[1]; // The maximum distance (diameter)
    }
    
    private int[] dfs(int node, int parent, int depth) {
        int farthestNode = node;
        int maxDepth = depth;
        
        for (int neighbor : graph[node]) {
            if (neighbor != parent) {
                int[] childResult = dfs(neighbor, node, depth + 1);
                if (childResult[1] > maxDepth) {
                    maxDepth = childResult[1];
                    farthestNode = childResult[0];
                }
            }
        }
        
        return new int[]{farthestNode, maxDepth};
    }
    
    private long pow(long a, int b, int mod) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return result;
    }
}
