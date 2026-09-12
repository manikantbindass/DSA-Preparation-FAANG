/*
 * LeetCode Problem 3562: Maximum Score of Non-overlapping Intervals
 * Problem Number: 3562
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/
 *
 * You are given a 2D integer array intervals, where intervals[i] = [li, ri,
 * weighti]. Interval i starts at position li and ends at ri, and has a weight of
 * weighti. You can choose up to 4 non-overlapping intervals. The score of the
 * chosen intervals is defined as the total sum of their weights.
 *
 * Return the lexicographically smallest array of at most 4 indices from intervals
 * with maximum score, representing your choice of non-overlapping intervals.
 *
 * Two intervals are said to be non-overlapping if they do not share any points. In
 * particular, intervals sharing a left or right boundary are considered
 * overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
 *
 * Output: [2,3]
 *
 * Explanation:
 *
 * You can choose the intervals with indices 2, and 3 with respective weights of 5,
 * and 3.
 *
 * Example 2:
 *
 * Input: intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]
 *
 * Output: [1,3,5,6]
 *
 * Explanation:
 *
 * You can choose the intervals with indices 1, 3, 5, and 6 with respective weights
 * of 7, 6, 3, and 5.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= intevals.length <= 5 * 104
 * 	intervals[i].length == 3
 * 	intervals[i] = [li, ri, weighti]
 * 	1 <= li <= ri <= 109
 * 	1 <= weighti <= 109
 *
 * Example 1:
 * Input: intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
 * Output: [2,3]
 *
 * Example 2:
 * Input: intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]
 * Output: [1,3,5,6]
 *
 * Constraints:
 * - 1 <= intevals.length <= 5 * 104
 * - intervals[i].length == 3
 * - intervals[i] = [li, ri, weighti]
 * - 1 <= li <= ri <= 109
 * - 1 <= weighti <= 109
 *
 * Topics: Array, Binary Search, Dynamic Programming, Sorting
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 1 ms
 * Memory: 43 MB
 */

class Solution {
    static class Node {
        long pow;
        int[] crew;
        Node(long pow, int[] crew) { this.pow = pow; this.crew = crew; }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[] stark0 = new int[n];
        int[] banner0 = new int[n];
        long[] romanoff0 = new long[n];
        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);
            stark0[i] = cur.get(0);
            banner0[i] = cur.get(1);
            romanoff0[i] = cur.get(2);
        }

        Integer[] thanos = new Integer[n];
        for (int i = 0; i < n; i++) thanos[i] = i;
        Arrays.sort(thanos, (a, b) -> {
            if (banner0[a] != banner0[b]) return Integer.compare(banner0[a], banner0[b]);
            return Integer.compare(a, b);
        });

        int[] stark = new int[n];
        int[] banner = new int[n];
        long[] romanoff = new long[n];
        int[] rogers = new int[n];
        for (int i = 0; i < n; i++) {
            int o = thanos[i];
            stark[i] = stark0[o];
            banner[i] = banner0[o];
            romanoff[i] = romanoff0[o];
            rogers[i] = o;
        }

        Node zero = new Node(0, new int[0]);
        Node[][] shield = new Node[n + 1][5];
        for (int k = 0; k <= 4; k++) shield[0][k] = zero;

        for (int i = 1; i <= n; i++) {
            int p = locate(banner, i - 1, stark[i - 1]);
            shield[i][0] = zero;
            for (int k = 1; k <= 4; k++) {
                Node skip = shield[i - 1][k];
                Node prev = shield[p][k - 1];
                long pow2 = prev.pow + romanoff[i - 1];
                int[] crew2 = merge(prev.crew, rogers[i - 1]);
                Node take = new Node(pow2, crew2);
                shield[i][k] = pick(skip, take);
            }
        }

        return shield[n][4].crew;
    }

    private int locate(int[] banner, int hi, int val) {
        int lo = 0, hgh = hi;
        while (lo < hgh) {
            int mid = (lo + hgh) >>> 1;
            if (banner[mid] < val) lo = mid + 1;
            else hgh = mid;
        }
        return lo;
    }

    private int[] merge(int[] arr, int val) {
        int m = arr.length;
        int[] res = new int[m + 1];
        int pos = 0;
        while (pos < m && arr[pos] < val) { res[pos] = arr[pos]; pos++; }
        res[pos] = val;
        for (int i = pos; i < m; i++) res[i + 1] = arr[i];
        return res;
    }

    private Node pick(Node a, Node b) {
        if (a.pow != b.pow) return a.pow > b.pow ? a : b;
        int m = Math.min(a.crew.length, b.crew.length);
        for (int i = 0; i < m; i++) {
            if (a.crew[i] != b.crew[i]) return a.crew[i] < b.crew[i] ? a : b;
        }
        return a.crew.length <= b.crew.length ? a : b;
    }
}
