/*
 * LeetCode Problem 1871: Jump Game VII
 * Problem Number: 1871
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/jump-game-vii/
 * 
 * You are given a 0-indexed binary string s and two integers minJump and maxJump.
 * In the beginning, you are at index 0, which is guaranteed to be '0'.
 * You can jump from index i to index j if:
 * - i + minJump <= j <= min(i + maxJump, s.length - 1)
 * - s[j] == '0'
 * 
 * Return true if you can reach index s.length - 1, otherwise return false.
 * 
 * Example 1:
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation: 0 -> 3 -> 5 (0-indexed positions)
 * 
 * Example 2:
 * Input: s = "011011", minJump = 2, maxJump = 3
 * Output: false
 * Explanation: Cannot reach last index because s[5] is '1'
 * 
 * Constraints:
 * - 2 <= s.length <= 10^5
 * - 1 <= minJump <= maxJump < s.length
 * - s[0] == '0'
 * 
 * Topics: String, Dynamic Programming, Prefix Sum, Sliding Window
 * Time Complexity: O(n) - using prefix sum for range queries
 * Space Complexity: O(n) - for DP and prefix arrays
 */

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        int[] prefix = new int[n + 1];
        
        // Starting position is always reachable
        dp[0] = true;
        prefix[1] = 1;
        
        for (int i = 1; i < n; ++i) {
            // Only consider positions where s[i] == '0'
            if (s.charAt(i) == '0') {
                int left = Math.max(0, i - maxJump);
                int right = i - minJump;
                
                // Check if there's any reachable position in the range [left, right]
                if (left <= right && prefix[right + 1] - prefix[left] > 0) {
                    dp[i] = true;
                }
            }
            // Update prefix sum
            prefix[i + 1] = prefix[i] + (dp[i] ? 1 : 0);
        }
        
        return dp[n - 1];
    }
}
