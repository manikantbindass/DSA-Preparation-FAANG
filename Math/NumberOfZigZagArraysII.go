/*
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
*/

package math

const mod = 1000000007

func zigZagArrays(n int, l int, r int) int {
    length := r - l + 1
    if length <= 0 {
        return 0
    }
    
    if n == 1 {
        return length % mod
    }
    
    // m1[i][j] = 1 if i < j (for increasing transitions)
    // m2[i][j] = 1 if i > j (for decreasing transitions)
    m1 := make([][]int64, length)
    m2 := make([][]int64, length)
    for i := 0; i < length; i++ {
        m1[i] = make([]int64, length)
        m2[i] = make([]int64, length)
        for j := 0; j < length; j++ {
            if i < j {
                m1[i][j] = 1
            }
            if i > j {
                m2[i][j] = 1
            }
        }
    }
    
    matMult := func(a, b [][]int64) [][]int64 {
        n := len(a)
        res := make([][]int64, n)
        for i := 0; i < n; i++ {
            res[i] = make([]int64, n)
        }
        for i := 0; i < n; i++ {
            for k := 0; k < n; k++ {
                if a[i][k] == 0 {
                    continue
                }
                for j := 0; j < n; j++ {
                    res[i][j] = (res[i][j] + a[i][k]*b[k][j]) % mod
                }
            }
        }
        return res
    }
    
    vecMult := func(vec []int64, mat [][]int64) []int64 {
        n := len(vec)
        res := make([]int64, n)
        for i := 0; i < n; i++ {
            if vec[i] == 0 {
                continue
            }
            for j := 0; j < n; j++ {
                res[j] = (res[j] + vec[i]*mat[i][j]) % mod
            }
        }
        return res
    }
    
    // Combined transition matrix: m = m1 * m2
    m := matMult(m1, m2)
    
    // Initial vector: all ones (starting from any value)
    vec := make([]int64, length)
    for i := 0; i < length; i++ {
        vec[i] = 1
    }
    
    // We need to process n-1 transitions
    steps := n - 1
    halfSteps := steps / 2
    
    // Fast exponentiation of the combined matrix
    power := m
    for halfSteps > 0 {
        if halfSteps%2 == 1 {
            vec = vecMult(vec, power)
        }
        power = matMult(power, power)
        halfSteps /= 2
    }
    
    // If steps is odd, apply one more m1 transition
    if steps%2 == 1 {
        vec = vecMult(vec, m1)
    }
    
    // Sum all elements to get total count
    var result int64 = 0
    for _, val := range vec {
        result = (result + val) % mod
    }
    
    return int((result * 2) % mod)
}
