// ──────────────────────────────────────────────────────────────────────
// LeetCode #4136 · Concatenate Non-Zero Digits and Multiply by Sum II
// Difficulty : Medium
// Topics     : Math, String, Prefix Sum
// URL        : https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We precompute prefix arrays for the sum of digits, count of non-zero
//   digits, and the concatenated value of non-zero digits modulo MOD. For
//   each query, we extract the substring's non-zero digit count and sum,
//   and compute the concatenated value using the prefix concatenation and
//   powers of 10. The answer is x * sum % MOD.
// 
// Complexity
//   Time  : O(n + q)
//   Space : O(n)
// 
// Runtime  : 41 ms
// Memory   : 43.6 MB
// 
// Examples
//   Example 1:
//     Input  : s = "10203004", queries = [[0,7],[1,3],[4,6]]
//     Output : [12340, 4, 9]
//   Example 2:
//     Input  : s = "1000", queries = [[0,3],[1,1]]
//     Output : [1, 0]
//   Example 3:
//     Input  : s = "9876543210", queries = [[0,9]]
//     Output : [444444137]
// 
// Constraints
//   · 1 <= m == s.length <= 105
//   · s consists of digits only.
//   · 1 <= queries.length <= 105
//   · queries[i] = [li, ri]
//   · 0 <= li <= ri < m
// ──────────────────────────────────────────────────────────────────────

func sumAndMultiply(s string, queries [][]int) []int {
    const MOD = 1000000007
    n := len(s)
    sumD := make([]int, n+1)
    cntN0 := make([]int, n+1)
    p := make([]int64, n+1)
    for i := 1; i <= n; i++ {
        d := int(s[i-1] - '0')
        sumD[i] = sumD[i-1] + d
        if d > 0 {
            cntN0[i] = cntN0[i-1] + 1
            p[i] = (p[i-1]*10 + int64(d)) % MOD
        } else {
            cntN0[i] = cntN0[i-1]
            p[i] = p[i-1]
        }
    }
    pow10 := make([]int64, n+1)
    pow10[0] = 1
    for i := 1; i <= n; i++ {
        pow10[i] = pow10[i-1] * 10 % MOD
    }
    ans := make([]int, len(queries))
    for i, q := range queries {
        l, r := q[0], q[1]
        n0 := cntN0[r+1] - cntN0[l]
        sd := sumD[r+1] - sumD[l]
        x := (p[r+1] - p[l]*pow10[n0]%MOD + MOD) % MOD
        ans[i] = int(x * int64(sd) % MOD)
    }
    return ans
}
