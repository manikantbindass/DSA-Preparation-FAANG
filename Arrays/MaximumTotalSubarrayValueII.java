/*
 * LeetCode Problem 3691: Maximum Total Subarray Value II
 * Problem Number: 3691
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/maximum-total-subarray-value-ii/
 * 
 * Given an array nums and an integer k, find the maximum total value by selecting k subarrays
 * where the value of a subarray is defined as its max - min.
 * 
 * Example:
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 8
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= k <= 10^5
 * 
 * Topics: Array, Sparse Table, Priority Queue, Greedy
 * Time Complexity: O(n log n + k log n) - building sparse table and processing k operations
 * Space Complexity: O(n log n) - for the sparse table
 */

import java.util.PriorityQueue;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTable sparseTable = new SparseTable(nums);
        // Priority queue stores {value, left, right} sorted by value descending
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        
        // Initial segment covering the entire array
        pq.offer(new int[]{sparseTable.query(0, n - 1), 0, n - 1});
        long sum = 0;
        
        for (int i = 0; i < k; i++) {
            int[] current = pq.poll();
            int value = current[0];
            int left = current[1];
            int right = current[2];
            sum += value;
            
            // Split the segment into two parts if possible
            if (left < right) {
                // Left part: from left to right-1
                pq.offer(new int[]{sparseTable.query(left, right - 1), left, right - 1});
                // Right part: from left+1 to right
                pq.offer(new int[]{sparseTable.query(left + 1, right), left + 1, right});
            }
        }
        
        return sum;
    }
}

class SparseTable {
    private final int[] pow;
    private final int[][] maxTable;
    private final int[][] minTable;
    
    public SparseTable(int[] arr) {
        int n = arr.length;
        // Precompute powers of 2
        this.pow = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            pow[i] = pow[i >> 1] + 1;
        }
        
        int maxLog = pow[n];
        this.maxTable = new int[maxLog + 1][n];
        this.minTable = new int[maxLog + 1][n];
        
        // Initialize level 0
        this.maxTable[0] = arr.clone();
        this.minTable[0] = arr.clone();
        
        // Build sparse table
        for (int p = 1; p <= maxLog; p++) {
            int len = n - (1 << p);
            int prevLen = 1 << (p - 1);
            for (int i = 0; i <= len; i++) {
                maxTable[p][i] = Math.max(maxTable[p - 1][i + prevLen], maxTable[p - 1][i]);
                minTable[p][i] = Math.min(minTable[p - 1][i + prevLen], minTable[p - 1][i]);
            }
        }
    }
    
    public int query(int left, int right) {
        int p = pow[right - left + 1];
        int maxVal = Math.max(maxTable[p][right - (1 << p) + 1], maxTable[p][left]);
        int minVal = Math.min(minTable[p][right - (1 << p) + 1], minTable[p][left]);
        return maxVal - minVal;
    }
}
