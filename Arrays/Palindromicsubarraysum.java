// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · palindromic-subarray-sum
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/palindromic-subarray-sum/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem asks for the maximum sum of a subarray after applying a
//   transformation based on a divisor k. For each possible k (all divisors
//   of numbers in the array plus the smallest missing positive integer not
//   a divisor of any number), we compute the maximum subarray sum using
//   Kadane's algorithm, where each element is replaced by its value if
//   divisible by k, otherwise by its negative. We then choose the k that
//   gives the maximum sum (with tie-breaking by smaller k). Finally, we
//   return (best_sum * k) mod 1e9+7.
// 
// Complexity
//   Time  : O(n * sqrt(m)) where m is the maximum value in nums, plus O(n * number_of_divisors)
//   Space : O(number_of_unique_divisors)
// 
// Runtime  : 0 ms
// Memory   : 42.6 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public int divisibleGame(int[] nums) {
        final long MOD = 1000000007L;
        Set<Integer> divisors = new HashSet<>();
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
            for (int d = 1; d * d <= x; d++) {
                if (x % d == 0) {
                    if (d > 1) divisors.add(d);
                    int e = x / d;
                    if (e > 1) divisors.add(e);
                }
            }
        }
        int missing = 2;
        while (divisors.contains(missing)) missing++;
        long bestSum = Long.MIN_VALUE;
        int bestK = missing;
        List<Integer> ks = new ArrayList<>(divisors);
        ks.add(missing);
        for (int k : ks) {
            long curMax = Long.MIN_VALUE;
            long curSum = 0;
            for (int x : nums) {
                long val = (x % k == 0) ? x : -x;
                if (curSum < 0) curSum = val;
                else curSum += val;
                if (curSum > curMax) curMax = curSum;
            }
            if (curMax > bestSum || (curMax == bestSum && k < bestK)) {
                bestSum = curMax;
                bestK = k;
            }
        }
        long ans = ((bestSum % MOD + MOD) % MOD) * bestK % MOD;
        return (int) ans;
    }
}
