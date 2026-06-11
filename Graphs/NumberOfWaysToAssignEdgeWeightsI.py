"""
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
"""

from typing import List

class Solution:
    MOD = 10**9 + 7
    
    def assignEdgeWeights(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        graph = [[] for _ in range(n + 1)]
        
        # Build adjacency list
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
        
        def dfs(node: int, parent: int, depth: int) -> tuple:
            """Returns (farthest_node, max_depth) from the given node"""
            farthest_node = node
            max_depth = depth
            
            for neighbor in graph[node]:
                if neighbor != parent:
                    child_node, child_depth = dfs(neighbor, node, depth + 1)
                    if child_depth > max_depth:
                        max_depth = child_depth
                        farthest_node = child_node
            
            return farthest_node, max_depth
        
        # First DFS to find farthest node from arbitrary node (1)
        farthest, _ = dfs(1, -1, 0)
        # Second DFS to find farthest distance from that node
        _, diameter = dfs(farthest, -1, 0)
        
        # Number of ways is 2^(diameter - 1) mod MOD
        return pow(2, diameter - 1, self.MOD)
