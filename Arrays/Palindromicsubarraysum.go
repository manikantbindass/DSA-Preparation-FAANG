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

package main

func divisibleGame(nums []int) int {
    const MOD = 1000000007
    divisors := make(map[int]bool)
    maxVal := 0
    for _, x := range nums {
        if x > maxVal {
            maxVal = x
        }
        for d := 1; d*d <= x; d++ {
            if x%d == 0 {
                if d > 1 {
                    divisors[d] = true
                }
                e := x / d
                if e > 1 {
                    divisors[e] = true
                }
            }
        }
    }
    missing := 2
    for divisors[missing] {
        missing++
    }
    bestSum := int64(-1 << 63)
    bestK := missing
    ks := make([]int, 0, len(divisors)+1)
    for k := range divisors {
        ks = append(ks, k)
    }
    ks = append(ks, missing)
    for _, k := range ks {
        curMax := int64(-1 << 63)
        curSum := int64(0)
        for _, x := range nums {
            var val int64
            if x%k == 0 {
                val = int64(x)
            } else {
                val = -int64(x)
            }
            if curSum < 0 {
                curSum = val
            } else {
                curSum += val
            }
            if curSum > curMax {
                curMax = curSum
            }
        }
        if curMax > bestSum || (curMax == bestSum && k < bestK) {
            bestSum = curMax
            bestK = k
        }
    }
    ans := ((bestSum % MOD + MOD) % MOD) * int64(bestK) % MOD
    return int(ans)
}
