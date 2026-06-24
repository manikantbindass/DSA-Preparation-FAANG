/*
 * LeetCode Problem 3700: Number of ZigZag Arrays II
 * Problem Number: 3700
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/number-of-zigzag-arrays-ii/
 * 
 * Given three integers n, l, and r, count the number of zigzag arrays of length n
 * where each element is between l and r (inclusive).
 * 
 * A zigzag array is defined as an array where for every i (0-indexed):
 * - If i is even, arr[i] < arr[i+1]
 * - If i is odd, arr[i] > arr[i+1]
 * 
 * Return the count modulo 10^9 + 7.
 * 
 * Constraints:
 * - 1 <= n <= 10^9
 * - 1 <= l <= r <= 10^5
 * 
 * Topics: Math, Matrix Exponentiation, Dynamic Programming, Combinatorics
 * Time Complexity: O(log n * len^3) - using matrix exponentiation
 * Space Complexity: O(len^2) - for the matrix
 */

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int zigZagArrays(int n, int l, int r) {
        int len = r - l + 1;
        if (len <= 0) return 0;
        
        // For n = 1, all numbers are valid zigzag arrays
        if (n == 1) {
            return len % MOD;
        }
        
        // m1[i][j] = 1 if i < j (for increasing transitions)
        // m2[i][j] = 1 if i > j (for decreasing transitions)
        long[][] m1 = new long[len][len];
        long[][] m2 = new long[len][len];
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i < j) m1[i][j] = 1;
                if (i > j) m2[i][j] = 1;
            }
        }
        
        // Combined transition matrix: m = m1 * m2
        long[][] m = multiply(m1, m2);
        
        // Initial vector: all ones (starting from any value)
        long[] vec = new long[len];
        Arrays.fill(vec, 1);
        
        // We need to process n-1 transitions
        int steps = n - 1;
        int halfSteps = steps / 2;
        
        // Fast exponentiation of the combined matrix
        long[][] power = m;
        while (halfSteps > 0) {
            if (halfSteps % 2 == 1) {
                vec = multiply(vec, power);
            }
            power = multiply(power, power);
            halfSteps /= 2;
        }
        
        // If steps is odd, apply one more m1 transition
        if (steps % 2 == 1) {
            vec = multiply(vec, m1);
        }
        
        // Sum all elements to get total count
        long result = 0;
        for (long val : vec) {
            result = (result + val) % MOD;
        }
        
        return (int) (result * 2 % MOD);
    }
    
    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
    
    private long[] multiply(long[] vec, long[][] mat) {
        int n = vec.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            if (vec[i] == 0) continue;
            for (int j = 0; j < n; j++) {
                res[j] = (res[j] + vec[i] * mat[i][j]) % MOD;
            }
        }
        return res;
    }
}
