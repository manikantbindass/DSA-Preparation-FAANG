/*
 * LeetCode Problem 0: minimum-adjacent-swaps-to-partition-array
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-adjacent-swaps-to-partition-array/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.5 MB
 */

class Solution {
    public long maximumValue(int n,int s,int m) {
        long ans = s;
        long k1 = n / 2;
        if (k1 > 0) {
            long v = (long) s+ k1* m- (k1 - 1);
            if (v > ans) ans= v;
        }
        long k2 =(n -1) / 2;
        if (k2 > 0) {
            long v= (long) s + k2 * m- k2;
            if (v> ans) ans = v;
        }
        return ans;
    }
}
