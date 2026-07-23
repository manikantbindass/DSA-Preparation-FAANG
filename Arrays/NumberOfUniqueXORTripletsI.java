/*
 * LeetCode Problem 3824: Number of Unique XOR Triplets I
 * Problem Number: 3824
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-unique-xor-triplets-i/
 *
 * You are given an integer array nums of length n, where nums is a permutation of
 * the numbers in the range [1, n].
 *
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR
 * nums[k] where i <= j <= k.
 *
 * Return the number of unique XOR triplet values from all possible triplets (i, j,
 * k).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The possible XOR triplet values are:
 *
 * 	(0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1
 * 	(0, 0, 1) &rarr; 1 XOR 1 XOR 2 = 2
 * 	(0, 1, 1) &rarr; 1 XOR 2 XOR 2 = 1
 * 	(1, 1, 1) &rarr; 2 XOR 2 XOR 2 = 2
 *
 * The unique XOR values are {1, 2}, so the output is 2.
 *
 * Example 2:
 *
 * Input: nums = [3,1,2]
 *
 * Output: 4
 *
 * Explanation:
 *
 * The possible XOR triplet values include:
 *
 * 	(0, 0, 0) &rarr; 3 XOR 3 XOR 3 = 3
 * 	(0, 0, 1) &rarr; 3 XOR 3 XOR 1 = 1
 * 	(0, 0, 2) &rarr; 3 XOR 3 XOR 2 = 2
 * 	(0, 1, 2) &rarr; 3 XOR 1 XOR 2 = 0
 *
 * The unique XOR values are {0, 1, 2, 3}, so the output is 4.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n == nums.length <= 105
 * 	1 <= nums[i] <= n
 * 	nums is a permutation of integers from 1 to n.
 *
 * Example 1:
 * Input: nums = [1,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,2]
 * Output: 4
 *
 * Constraints:
 * - 1 <= n == nums.length <= 105
 * - 1 <= nums[i] <= n
 * - nums is a permutation of integers from 1 to n.
 *
 * Topics: Array, Math, Bit Manipulation
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.8 MB
 */

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;
        if (n ==1) return 1;
        if (n== 2) return 2;
        int a = 1;
        while (a<=n) a <<= 1;
        return a;
    }
}
