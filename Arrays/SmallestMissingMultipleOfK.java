/*
 * LeetCode Problem 4080: Smallest Missing Multiple of K
 * Problem Number: 4080
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/smallest-missing-multiple-of-k/
 *
 * Given an integer array nums and an integer k, return the smallest positive
 * multiple of k that is missing from nums.
 *
 * A multiple of k is any positive integer divisible by k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,3,4,6], k = 2
 *
 * Output: 10
 *
 * Explanation:
 *
 * The multiples of k = 2 are 2, 4, 6, 8, 10, 12... and the smallest multiple
 * missing from nums is 10.
 *
 * Example 2:
 *
 * Input: nums = [1,4,7,10,15], k = 5
 *
 * Output: 5
 *
 * Explanation:
 *
 * The multiples of k = 5 are 5, 10, 15, 20... and the smallest multiple missing
 * from nums is 5.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 100
 * 	1 <= nums[i] <= 100
 * 	1 <= k <= 100
 *
 * Example 1:
 * Input: nums = [8,2,3,4,6], k = 2
 * Output: 10
 *
 * Example 2:
 * Input: nums = [1,4,7,10,15], k = 5
 * Output: 5
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 100
 * - 1 <= k <= 100
 *
 * Topics: Array, Hash Table
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * Runtime: 0 ms
 * Memory: 42.6 MB
 */

class Solution {
    public int missingMultiple(int[] nums,int k) {
        boolean[] s=new boolean[101];
        for (int x:nums) {
            s[x]=true;
        }
        for (int i=1;; ++i) {
            int x=k*i;
            if (x >= s.length ||!s[x]) {
                return x;
            }
        }
    }
}
