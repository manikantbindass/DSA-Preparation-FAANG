// ──────────────────────────────────────────────────────────────────────
// LeetCode #204 · Count Primes
// Difficulty : Medium
// Topics     : Array, Math, Enumeration, Number Theory
// URL        : https://leetcode.com/problems/count-primes/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use the Sieve of Eratosthenes algorithm. Create a boolean array of
//   size n (since we only care about numbers < n) and initially mark all
//   entries as true (potential primes). Start from 2, the first prime. For
//   each prime i, mark all multiples of i (starting from i*i to avoid
//   redundant marking) as false. Count the number of true entries. This
//   efficiently finds all primes up to n-1.
// 
// Complexity
//   Time  : O(n log log n)
//   Space : O(n)
// 
// Runtime  : 0 ms
// Memory   : 42.3 MB
// 
// Examples
//   Example 1:
//     Input  : n = 10
//     Output : 4
//     Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//   Example 2:
//     Input  : n = 0
//     Output : 0
//   Example 3:
//     Input  : n = 1
//     Output : 0
// 
// Constraints
//   · 0 <= n <= 5 * 106
// ──────────────────────────────────────────────────────────────────────

func countPrimes(n int) int {
    if n < 2 {
        return 0
    }
    isPrime := make([]bool, n)
    for i := 2; i < n; i++ {
        isPrime[i] = true
    }
    count := 0
    for i := 2; i < n; i++ {
        if isPrime[i] {
            count++
            if i*i < n {
                for j := i * i; j < n; j += i {
                    isPrime[j] = false
                }
            }
        }
    }
    return count
}
