/*
 * LeetCode Problem 0: count-subarrays-with-even-odd-ratio-i
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-i/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.7 MB
 */

class Solution {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int n= nums.length;
        int ans= 0;
        for (int i = 0;i < n; i++) {
            int e =0,o = 0;
            for (int j= i;j < n;j++) {
                if ((nums[j] & 1) == 0) e++;
                else o++;
                if (o>0 && (long)e *b <=(long)o *a) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
