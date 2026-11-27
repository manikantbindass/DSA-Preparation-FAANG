/*
 * LeetCode Problem 3934: Smallest Unique Subarray
 * Problem Number: 3934
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/smallest-unique-subarray/
 *
 * You are given an integer array nums.
 *
 * Find the minimum length of a subarray that is not identical to any other
 * subarray in nums.
 *
 * Return an integer denoting the minimum possible length of such a subarray.
 *
 * Two subarrays are considered identical if they have the same length and the same
 * elements in corresponding positions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,3,3]
 *
 * Output: 3
 *
 * Explanation:
 *
 * 	Subarrays of length 1: [3] &rarr; appears 3 times
 * 	Subarrays of length 2: [3, 3] &rarr; appears 2 times
 * 	Subarrays of length 3: [3, 3, 3] &rarr; appears once
 *
 * The subarray [3, 3, 3] is unique, so the smallest unique subarray length is 3.
 *
 * Example 2:
 *
 * Input: nums = [2,1,2,3,3]
 *
 * Output: 1
 *
 * Explanation:
 *
 * Subarrays of length 1:
 *
 * 	[2] &rarr; appears 2 times
 * 	[1] &rarr; appears once
 * 	[3] &rarr; appears 2 times
 *
 * The subarray [1] is unique, so the smallest unique subarray length is 1.
 *
 * Example 3:
 *
 * Input: nums = [1,1,2,2,1]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Subarrays of length 1:
 *
 * 	[1] &rarr; appears 3 times
 * 	[2] &rarr; appears 2 times
 *
 * Subarrays of length 2:
 *
 * 	[1, 1] &rarr; appears once
 * 	[1, 2] &rarr; appears once
 * 	[2, 2] &rarr; appears once
 * 	[2, 1] &rarr; appears once
 *
 * There is at least one subarray of length 2 that is unique, so the smallest
 * unique subarray length is 2.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 105
 *
 * Example 1:
 * Input: nums = [3,3,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,1,2,3,3]
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,1,2,2,1]
 * Output: 2
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - 1 <= nums[i] <= 105
 *
 * Topics: Array, Hash Table, Binary Search, Rolling Hash, Suffix Array, Hash Function
 * Time Complexity: O(n log n + M), where M is the maximum value
 * Space Complexity: O(n + M)
 * Approach: Build a suffix array by rank doubling, then use neighboring common prefix lengths to find unique prefixes.
 */

import java.util.Arrays;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int m = 0;
        for (int v : nums) m = Math.max(m, v);
        m++;
        int[] sa = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        int[] c = new int[Math.max(m, n) + 1];
        for (int i = 0; i < n; i++) c[x[i] = nums[i]]++;
        for (int i = 1; i < m; i++) c[i] += c[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--c[x[i]]] = i;
        for (int k = 1; k <= n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) y[p++] = i;
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) y[p++] = sa[i] - k;
            }
            Arrays.fill(c, 0, m, 0);
            for (int i = 0; i < n; i++) c[x[y[i]]]++;
            for (int i = 1; i < m; i++) c[i] += c[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--c[x[y[i]]]] = y[i];
            int[] t = x;
            x = y;
            y = t;
            p = 1;
            x[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1], b = sa[i];
                int ra = a + k < n ? y[a + k] : -1;
                int rb = b + k < n ? y[b + k] : -1;
                if (y[a] == y[b] && ra == rb) x[b] = p - 1;
                else x[b] = p++;
            }
            if (p >= n) break;
            m = p;
        }
        int[] rk = new int[n];
        for (int i = 0; i < n; i++) rk[sa[i]] = i;
        // lcp[r] is the common prefix with the preceding suffix.
        int[] lcp = new int[n + 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rk[i] > 0) {
                int j = sa[rk[i] - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) h++;
                lcp[rk[i]] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }
        int ans = n;
        for (int r = 0; r < n; r++) {
            int need = Math.max(lcp[r], lcp[r + 1]) + 1;
            if (sa[r] + need <= n) ans = Math.min(ans, need);
        }
        return ans;
    }
}
