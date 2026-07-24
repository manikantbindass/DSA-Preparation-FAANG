/*
 * LeetCode Problem 3820: Number of Unique XOR Triplets II
 * Problem Number: 3820
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-unique-xor-triplets-ii/
 *
 * You are given an integer array nums.
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
 * Input: nums = [1,3]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The possible XOR triplet values are:
 *
 * 	(0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1
 * 	(0, 0, 1) &rarr; 1 XOR 1 XOR 3 = 3
 * 	(0, 1, 1) &rarr; 1 XOR 3 XOR 3 = 1
 * 	(1, 1, 1) &rarr; 3 XOR 3 XOR 3 = 3
 *
 * The unique XOR values are {1, 3}. Thus, the output is 2.
 *
 * Example 2:
 *
 * Input: nums = [6,7,8,9]
 *
 * Output: 4
 *
 * Explanation:
 *
 * The possible XOR triplet values are {6, 7, 8, 9}. Thus, the output is 4.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 1500
 * 	1 <= nums[i] <= 1500
 *
 * Example 1:
 * Input: nums = [1,3]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [6,7,8,9]
 * Output: 4
 *
 * Constraints:
 * - 1 <= nums.length <= 1500
 * - 1 <= nums[i] <= 1500
 *
 * Topics: Array, Math, Bit Manipulation, Enumeration
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.7 MB
 */

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int mx= 0;
        for (int x:nums) {
            mx =Math.max(mx, x);
        }
        mx <<=1;
        boolean[] st=new boolean[mx];
        for (int a :nums) {
            for (int b :nums) {
                st[a ^ b]=true;
            }
        }
        int[] s= new int[mx];
        for (int ab=0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c: nums) {
                    s[ab ^ c]= 1;
                }
            }
        }
        int ans=0;
        for (int v: s) {
            ans += v;
        }
        return ans;
    }
}
