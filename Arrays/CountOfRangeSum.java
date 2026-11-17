/*
 * LeetCode Problem 327: Count of Range Sum
 * Problem Number: 327
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/count-of-range-sum/
 *
 * Given an integer array nums and two integers lower and upper, return the number
 * of range sums that lie in [lower, upper] inclusive.
 *
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices
 * i and j inclusive, where i <= j.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective
 * sums are: -2, -1, 2.
 *
 * Example 2:
 *
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	-231 <= nums[i] <= 231 - 1
 * 	-105 <= lower <= upper <= 105
 * 	The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 *
 * Example 2:
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -231 <= nums[i] <= 231 - 1
 * - -105 <= lower <= upper <= 105
 * - The answer is
 *
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree, Merge Sort, Ordered Set, Treap
 * Time Complexity: O(log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 41.3 MB
 */

class BinaryIndexedTree {
    private int n;
    private int[] c;
    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }
    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }
    public int query(int x) {
        int s = 0;
        while (x != 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long[] arr = new long[n * 3 + 3];
        for (int i = 0, j = 0; i <= n; ++i, j += 3) {
            arr[j] = s[i];
            arr[j + 1] = s[i] - lower;
            arr[j + 2] = s[i] - upper;
        }
        Arrays.sort(arr);
        int m = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[m++] = arr[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        int ans = 0;
        for (long x : s) {
            int l = search(arr, m, x - upper);
            int r = search(arr, m, x - lower);
            ans += tree.query(r) - tree.query(l - 1);
            tree.update(search(arr, m, x), 1);
        }
        return ans;
    }
    private int search(long[] nums, int r, long x) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
