/*
 * LeetCode Problem 1345: Jump Game IV
 * Problem Number: 1345
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/jump-game-iv/
 * 
 * Given an array of integers arr, you are initially positioned at the first index.
 * In one step, you can jump from index i to:
 * - i + 1 where i + 1 < arr.length
 * - i - 1 where i - 1 >= 0
 * - j where arr[i] == arr[j] and i != j
 * 
 * Return the minimum number of steps to reach the last index of the array.
 * 
 * Example 1:
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: 0 -> 4 -> 3 -> 9 (0-indexed positions)
 * 
 * Example 2:
 * Input: arr = [7]
 * Output: 0
 * 
 * Example 3:
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: 0 -> 7 (jump directly to last index)
 * 
 * Constraints:
 * - 1 <= arr.length <= 5 * 10^4
 * - -10^8 <= arr[i] <= 10^8
 * 
 * Topics: Array, Hash Table, Breadth-First Search
 * Time Complexity: O(n) - each index is visited at most once
 * Space Complexity: O(n) - for the graph map and queue
 */

import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        
        // Build graph: value -> list of indices
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        
        for (int steps = 0; !queue.isEmpty(); steps++) {
            for (int size = queue.size(); size > 0; size--) {
                int i = queue.poll();
                
                // Reached last index
                if (i == n - 1) {
                    return steps;
                }
                
                // Jump to indices with same value
                for (int j : graph.get(arr[i])) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
                // Clear to avoid re-processing same values
                graph.get(arr[i]).clear();
                
                // Jump to adjacent indices
                for (int j : new int[]{i - 1, i + 1}) {
                    if (j >= 0 && j < n && !visited[j]) {
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
        
        return -1; // Unreachable (should not happen for valid input)
    }
}
