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

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MX = 100001;
    private static final long[] POW10 = new long[MX];
    static {
        POW10[0] = 1;
        for (int i = 1; i < MX; i++) {
            POW10[i] = POW10[i - 1] * 10 % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] sumD = new int[n + 1];
        int[] cntN0 = new int[n + 1];
        long[] p = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            sumD[i] = sumD[i - 1] + d;
            cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
            p[i] = d > 0 ? (p[i - 1] * 10 + d) % MOD : p[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int n0 = cntN0[r + 1] - cntN0[l];
            int sd = sumD[r + 1] - sumD[l];
            long x = (p[r + 1] - p[l] * POW10[n0] % MOD + MOD) % MOD;
            ans[i] = (int) (x * sd % MOD);
        }
        return ans;
    }
}
