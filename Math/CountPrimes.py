# ──────────────────────────────────────────────────────────────────────
# LeetCode #204 · Count Primes
# Difficulty : Medium
# Topics     : Array, Math, Enumeration, Number Theory
# URL        : https://leetcode.com/problems/count-primes/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use the Sieve of Eratosthenes algorithm. Create a boolean array of
#   size n, initially all true. Iterate from 2 to n-1; if the number is
#   still marked prime, increment the count and mark all its multiples as
#   non-prime. Return the count.
# 
# Complexity
#   Time  : O(n log log n)
#   Space : O(n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : n = 10
#     Output : 4
#     Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
#   Example 2:
#     Input  : n = 0
#     Output : 0
#   Example 3:
#     Input  : n = 1
#     Output : 0
# 
# Constraints
#   · 0 <= n <= 5 * 106
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        is_prime = [True] * n
        count = 0
        for i in range(2, n):
            if is_prime[i]:
                count += 1
                if i * i < n:
                    for j in range(i * i, n, i):
                        is_prime[j] = False
        return count
