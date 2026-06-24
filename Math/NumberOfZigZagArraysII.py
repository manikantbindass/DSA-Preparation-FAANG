"""
LeetCode Problem 3700: Number of ZigZag Arrays II
Problem Number: 3700
Difficulty: Hard
Link: https://leetcode.com/problems/number-of-zigzag-arrays-ii/

Given three integers n, l, and r, count the number of zigzag arrays of length n
where each element is between l and r (inclusive).

A zigzag array is defined as an array where for every i (0-indexed):
- If i is even, arr[i] < arr[i+1]
- If i is odd, arr[i] > arr[i+1]

Return the count modulo 10^9 + 7.

Constraints:
- 1 <= n <= 10^9
- 1 <= l <= r <= 10^5

Topics: Math, Matrix Exponentiation, Dynamic Programming, Combinatorics
Time Complexity: O(log n * len^3) - using matrix exponentiation
Space Complexity: O(len^2) - for the matrix
"""

MOD = 10**9 + 7

class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        length = r - l + 1
        if length <= 0:
            return 0
        
        if n == 1:
            return length % MOD
        
        # m1[i][j] = 1 if i < j (for increasing transitions)
        # m2[i][j] = 1 if i > j (for decreasing transitions)
        m1 = [[0] * length for _ in range(length)]
        m2 = [[0] * length for _ in range(length)]
        
        for i in range(length):
            for j in range(length):
                if i < j:
                    m1[i][j] = 1
                if i > j:
                    m2[i][j] = 1
        
        def mat_mult(a, b):
            n = len(a)
            res = [[0] * n for _ in range(n)]
            for i in range(n):
                for k in range(n):
                    if a[i][k] == 0:
                        continue
                    for j in range(n):
                        res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD
            return res
        
        def vec_mult(vec, mat):
            n = len(vec)
            res = [0] * n
            for i in range(n):
                if vec[i] == 0:
                    continue
                for j in range(n):
                    res[j] = (res[j] + vec[i] * mat[i][j]) % MOD
            return res
        
        # Combined transition matrix: m = m1 * m2
        m = mat_mult(m1, m2)
        
        # Initial vector: all ones (starting from any value)
        vec = [1] * length
        
        # We need to process n-1 transitions
        steps = n - 1
        half_steps = steps // 2
        
        # Fast exponentiation of the combined matrix
        power = m
        while half_steps > 0:
            if half_steps % 2 == 1:
                vec = vec_mult(vec, power)
            power = mat_mult(power, power)
            half_steps //= 2
        
        # If steps is odd, apply one more m1 transition
        if steps % 2 == 1:
            vec = vec_mult(vec, m1)
        
        # Sum all elements to get total count
        result = sum(vec) % MOD
        return (result * 2) % MOD
