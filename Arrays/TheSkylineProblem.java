// ──────────────────────────────────────────────────────────────────────
// LeetCode #218 · The Skyline Problem
// Difficulty : Hard
// Topics     : Array, Divide and Conquer, Binary Indexed Tree, Segment Tree, Sweep Line, Sorting, Heap (Priority Queue), Ordered Set
// URL        : https://leetcode.com/problems/the-skyline-problem/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a sweep line algorithm. First, collect all unique x-coordinates
//   (left and right edges) from all buildings and sort them. Then iterate
//   through these x-coordinates in order. Maintain a max-heap (priority
//   queue) of active buildings, where each entry is a tuple (negative
//   height, left, right). For each x, add all buildings whose left edge ≤
//   current x to the heap. Remove buildings whose right edge ≤ current x
//   (they are no longer active). The current height is the max height
//   among active buildings (top of heap, negated). If the height changes
//   from the previous key point, add a new key point [x, current height].
//   This ensures no consecutive horizontal lines of equal height.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//     Output : [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//   Example 2:
//     Input  : buildings = [[0,2,3],[2,5,3]]
//     Output : [[0,3],[5,0]]
// 
// Constraints
//   · 1 <= buildings.length <= 104
//   · 0 <= lefti < righti <= 231 - 1
//   · 1 <= heighti <= 231 - 1
//   · buildings is sorted by lefti in non-decreasing order.
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> xs = new ArrayList<>();
        for (int[] b : buildings) {
            xs.add(b[0]);
            xs.add(b[1]);
        }
        Collections.sort(xs);
        
        // max-heap: store (-height, left, right)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        
        int idx = 0, n = buildings.length;
        for (int x : xs) {
            // add all buildings starting at or before x
            while (idx < n && buildings[idx][0] <= x) {
                pq.offer(new int[]{-buildings[idx][2], buildings[idx][0], buildings[idx][1]});
                idx++;
            }
            // remove buildings that end at or before x
            while (!pq.isEmpty() && pq.peek()[2] <= x) {
                pq.poll();
            }
            int h = pq.isEmpty() ? 0 : -pq.peek()[0];
            if (result.isEmpty() || result.get(result.size() - 1).get(1) != h) {
                result.add(Arrays.asList(x, h));
            }
        }
        return result;
    }
}
