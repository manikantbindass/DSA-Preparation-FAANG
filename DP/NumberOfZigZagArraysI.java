/*
 * LeetCode Problem 3699: Number of ZigZag Arrays I
 * Problem Number: 3699
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/number-of-zigzag-arrays-i/
 * 
 * A zigzag array is an array where every adjacent pair of elements alternates
 * between greater and less than the previous element.
 * 
 * Given n (length of array) and a range [l, r] of possible values,
 * count the number of zigzag arrays of length n where each element is in [l, r].
 * 
 * Example:
 * Input: n = 3, l = 1, r = 3
 * Output: 10
 * 
 * Constraints:
 * - 1 <= n <= 10^5
 * - 1 <= l <= r <= 10^5
 * 
 * Topics: Dynamic Programming, Math, Combinatorics
 * Time Complexity: O(n * m) - where m = r - l + 1
 * Space Complexity: O(m) - for DP arrays
 */

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        
        // dpUp[i] = number of valid sequences of current length ending with value i
        // where the last step was an upward move (current > previous)
        // dpDown[i] = number of valid sequences ending with value i
        // where the last step was a downward move (current < previous)
        long[] dpUp = new long[m];
        long[] dpDown = new long[m];
        
        // Base case: length 1 - each value can be a starting point
        for (int i = 0; i < m; i++) {
            dpUp[i] = 1;
            dpDown[i] = 1;
        }
        
        // Build sequences of length 2 to n
        for (int step = 2; step <= n; step++) {
            // Compute prefix sums for dpDown to optimize range queries
            long[] prefixDown = new long[m + 1];
            for (int i = 0; i < m; i++) {
                prefixDown[i + 1] = (prefixDown[i] + dpDown[i]) % MOD;
            }
            
            // Compute suffix sums for dpUp
            long[] suffixUp = new long[m + 1];
            for (int i = m - 1; i >= 0; i--) {
                suffixUp[i] = (suffixUp[i + 1] + dpUp[i]) % MOD;
            }
            
            long[] newUp = new long[m];
            long[] newDown = new long[m];
            
            for (int x = 0; x < m; x++) {
                // For downward move: previous value must be greater than current
                // Sum of dpUp[x+1] + dpUp[x+2] + ... + dpUp[m-1]
                newDown[x] = suffixUp[x + 1];
                
                // For upward move: previous value must be less than current
                // Sum of dpDown[0] + dpDown[1] + ... + dpDown[x-1]
                newUp[x] = prefixDown[x];
            }
            
            dpUp = newUp;
            dpDown = newDown;
        }
        
        // Sum all sequences of length n
        long total = 0;
        for (int i = 0; i < m; i++) {
            total = (total + dpUp[i] + dpDown[i]) % MOD;
        }
        
        return (int) total;
    }
}
