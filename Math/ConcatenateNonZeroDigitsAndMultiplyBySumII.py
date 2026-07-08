# ──────────────────────────────────────────────────────────────────────
# LeetCode #4136 · Concatenate Non-Zero Digits and Multiply by Sum II
# Difficulty : Medium
# Topics     : Math, String, Prefix Sum
# URL        : https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We precompute prefix arrays for the sum of digits, count of non-zero
#   digits, and the concatenated value of non-zero digits modulo MOD. For
#   each query, we extract the substring's non-zero digit count and sum,
#   and compute the concatenated value using the prefix concatenation and
#   powers of 10. The answer is x * sum % MOD.
# 
# Complexity
#   Time  : O(n + q)
#   Space : O(n)
# 
# Runtime  : 41 ms
# Memory   : 43.6 MB
# 
# Examples
#   Example 1:
#     Input  : s = "10203004", queries = [[0,7],[1,3],[4,6]]
#     Output : [12340, 4, 9]
#   Example 2:
#     Input  : s = "1000", queries = [[0,3],[1,1]]
#     Output : [1, 0]
#   Example 3:
#     Input  : s = "9876543210", queries = [[0,9]]
#     Output : [444444137]
# 
# Constraints
#   · 1 <= m == s.length <= 105
#   · s consists of digits only.
#   · 1 <= queries.length <= 105
#   · queries[i] = [li, ri]
#   · 0 <= li <= ri < m
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        MOD = 10**9 + 7
        n = len(s)
        sumD = [0] * (n + 1)
        cntN0 = [0] * (n + 1)
        p = [0] * (n + 1)
        for i in range(1, n + 1):
            d = ord(s[i - 1]) - 48
            sumD[i] = sumD[i - 1] + d
            cntN0[i] = cntN0[i - 1] + (1 if d > 0 else 0)
            if d > 0:
                p[i] = (p[i - 1] * 10 + d) % MOD
            else:
                p[i] = p[i - 1]
        pow10 = [1] * (n + 1)
        for i in range(1, n + 1):
            pow10[i] = pow10[i - 1] * 10 % MOD
        ans = []
        for l, r in queries:
            n0 = cntN0[r + 1] - cntN0[l]
            sd = sumD[r + 1] - sumD[l]
            x = (p[r + 1] - p[l] * pow10[n0]) % MOD
            ans.append((x * sd) % MOD)
        return ans
