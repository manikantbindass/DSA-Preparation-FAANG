// ──────────────────────────────────────────────────────────────────────
// LeetCode #3838 · Path Existence Queries in a Graph I
// Difficulty : Medium
// Topics     : Array, Hash Table, Binary Search, Union-Find, Graph Theory
// URL        : https://leetcode.com/problems/path-existence-queries-in-a-graph-i/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The graph is defined by edges between consecutive nodes in the sorted
//   nums array if their difference <= maxDiff. This creates connected
//   components that are contiguous segments of the array. We can assign a
//   component ID to each node by scanning the array and incrementing the
//   ID whenever a gap > maxDiff is found. Then for each query, we just
//   check if the two nodes have the same component ID.
// 
// Complexity
//   Time  : O(n + q)
//   Space : O(n)
// 
// Runtime  : 0 ms
// Memory   : 42.8 MB
// 
// Examples
//   Example 1:
//     Input  : n = 2, nums = [1,3], maxDiff = 1, queries = [[0,0],[0,1]]
//     Output : [true,false]
//   Example 2:
//     Input  : n = 4, nums = [2,5,6,8], maxDiff = 2, queries = [[0,1],[0,2],[1,3],[2,3]]
//     Output : [false,false,true,true]
// 
// Constraints
//   · 1 <= n == nums.length <= 105
//   · 0 <= nums[i] <= 105
//   · nums is sorted in
// ──────────────────────────────────────────────────────────────────────

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] comp = new int[n];
        int id = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                id++;
            }
            comp[i] = id;
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = comp[u] == comp[v];
        }
        return ans;
    }
}
