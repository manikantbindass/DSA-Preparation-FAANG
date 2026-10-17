/*
 * LeetCode Problem 3847: Minimum Swaps to Sort by Digit Sum
 * Problem Number: 3847
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/
 *
 * You are given an array nums of distinct positive integers. You need to sort the
 * array in increasing order based on the sum of the digits of each number. If two
 * numbers have the same digit sum, the smaller number appears first in the sorted
 * order.
 *
 * Return the minimum number of swaps required to rearrange nums into this sorted
 * order.
 *
 * A swap is defined as exchanging the values at two distinct positions in the
 * array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [37,100]
 *
 * Output: 1
 *
 * Explanation:
 *
 * 	Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] &rarr; [10,
 * 1]
 * 	Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the
 * sorted order.
 * 	Thus, the minimum number of swaps required to rearrange nums is 1.
 *
 * Example 2:
 *
 * Input: nums = [22,14,33,7]
 *
 * Output: 0
 *
 * Explanation:
 *
 * 	Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 =
 * 7] &rarr; [4, 5, 6, 7]
 * 	Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already
 * sorted.
 * 	Thus, the minimum number of swaps required to rearrange nums is 0.
 *
 * Example 3:
 *
 * Input: nums = [18,43,34,16]
 *
 * Output: 2
 *
 * Explanation:
 *
 * 	Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6
 * = 7] &rarr; [9, 7, 7, 7]
 * 	Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and
 * swap 43 with 34 to obtain the sorted order.
 * 	Thus, the minimum number of swaps required to rearrange nums is 2.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 109
 * 	nums consists of distinct positive integers.
 *
 * Example 1:
 * Input: nums = [37,100]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [22,14,33,7]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [18,43,34,16]
 * Output: 2
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - 1 <= nums[i] <= 109
 * - nums consists of
 *
 * Topics: Array, Hash Table, Sorting
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 * Runtime: 1 ms
 * Memory: 41.2 MB
 */

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = f(nums[i]);
            arr[i][1] = nums[i];
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; i++) {
            d.put(arr[i][1], i);
        }
        boolean[] vis = new boolean[n];
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans--;
                int j = i;
                while (!vis[j]) {
                    vis[j] = true;
                    j = d.get(nums[j]);
                }
            }
        }
        return ans;
    }

    private int f(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
