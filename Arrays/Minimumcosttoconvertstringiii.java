/*
 * LeetCode Problem 0: minimum-cost-to-convert-string-iii
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-cost-to-convert-string-iii/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.8 MB
 */

class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        long mod = 1_000_000_007L;
        long c1 = 0, c2= 0,res= 0;
        for (int x: nums) {
            if (x > b) {
                c2++;
            } else if (x>= a) {
                res+= c2;
                c1++;
            } else {
                res+= c1+ c2;
            }
        }
        return (int) (res % mod);
    }
}
