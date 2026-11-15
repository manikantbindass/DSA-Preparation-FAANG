/*
 * LeetCode Problem 1631: Number of Sub-arrays With Odd Sum
 * Problem Number: 1631
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 *
 * Given an array of integers arr, return the number of subarrays with an odd sum.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 *
 * Example 2:
 *
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 *
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 *
 *
 *
 * Constraints:
 *
 * 	1 <= arr.length <= 105
 * 	1 <= arr[i] <= 100
 *
 * Example 1:
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 *
 * Example 2:
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 *
 * Example 3:
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 *
 * Constraints:
 * - 1 <= arr.length <= 105
 * - 1 <= arr[i] <= 100
 *
 * Topics: Array, Math, Dynamic Programming, Prefix Sum
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 0 ms
 * Memory: 42.1 MB
 */

class Solution {
    public int numOfSubarrays(int[] arr) {
        int[] cnt = {1, 0};
        int ans = 0, s = 0;
        final int mod = (int) 1e9 + 7;
        for (int x : arr) {
            s = (s + x) % 2;
            ans = (ans + cnt[s ^ 1]) % mod;
            ++cnt[s];
        }
        return ans;
    }
}
