/*
 * LeetCode Problem 3753: Total Waviness of Numbers in Range II
 * Problem Number: 3753
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/
 * 
 * The waviness of a number is defined as the count of its digits that are either:
 * - Strictly greater than both adjacent digits (peak), or
 * - Strictly less than both adjacent digits (valley)
 * 
 * Given two integers num1 and num2, return the total waviness of all numbers
 * in the inclusive range [num1, num2].
 * 
 * Example:
 * Input: num1 = 10, num2 = 50
 * Output: 38
 * 
 * Constraints:
 * - 1 <= num1 <= num2 <= 10^5 (for Part I)
 * - For Part II, constraints are larger, requiring digit DP.
 * 
 * Topics: Dynamic Programming, Digit DP, Math, String
 * Time Complexity: O(log10(num2) * 2 * 2 * 11 * 11) - constant for digit DP
 * Space Complexity: O(log10(num2) * 2 * 2 * 11 * 11) - memoization size
 */

class Solution {
    private char[] digits;
    private long[][][][][][] memo;
    
    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
    
    private long solve(long n) {
        if (n <= 0) return 0;
        digits = Long.toString(n).toCharArray();
        int len = digits.length;
        // memo[pos][tight][started][prev2][prev1][0/1 for waviness count?]
        // Actually we need to store {wavinessSum, count}
        // Using 6D array: pos, tight, started, prev2, prev1, 0/1 for sum/count
        memo = new long[len][2][2][11][11][2];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int p2 = 0; p2 < 11; p2++) {
                        for (int p1 = 0; p1 < 11; p1++) {
                            memo[i][j][k][p2][p1][0] = -1;
                            memo[i][j][k][p2][p1][1] = -1;
                        }
                    }
                }
            }
        }
        long[] result = dfs(0, 1, 0, 10, 10);
        return result[0];
    }
    
    private long[] dfs(int pos, int tight, int started, int prev2, int prev1) {
        if (pos == digits.length) {
            // Return {wavinessSum, count} - base case: 1 way to form empty number
            return new long[]{0, 1};
        }
        
        if (memo[pos][tight][started][prev2][prev1][0] != -1) {
            return new long[]{
                memo[pos][tight][started][prev2][prev1][0],
                memo[pos][tight][started][prev2][prev1][1]
            };
        }
        
        long wavinessSum = 0;
        long count = 0;
        int maxDigit = tight == 1 ? digits[pos] - '0' : 9;
        
        for (int d = 0; d <= maxDigit; d++) {
            int newTight = (tight == 1 && d == maxDigit) ? 1 : 0;
            
            if (started == 0 && d == 0) {
                // Skip leading zeros
                long[] res = dfs(pos + 1, newTight, 0, 10, 10);
                wavinessSum += res[0];
                count += res[1];
            } else {
                int newPrev2 = (started == 0) ? 10 : prev1;
                int newPrev1 = d;
                long addWaviness = 0;
                
                // Check if current digit creates a waviness with previous two digits
                if (started == 1 && prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d)) {
                        addWaviness = 1;
                    }
                }
                
                long[] res = dfs(pos + 1, newTight, 1, newPrev2, newPrev1);
                wavinessSum += res[0] + addWaviness * res[1];
                count += res[1];
            }
        }
        
        memo[pos][tight][started][prev2][prev1][0] = wavinessSum;
        memo[pos][tight][started][prev2][prev1][1] = count;
        return new long[]{wavinessSum, count};
    }
}
