# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · palindromic-subarray-sum
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/palindromic-subarray-sum/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem asks for the maximum sum of a subarray after applying a
#   transformation based on a divisor k. For each possible k (all divisors
#   of numbers in the array plus the smallest missing positive integer not
#   a divisor of any number), we compute the maximum subarray sum using
#   Kadane's algorithm, where each element is replaced by its value if
#   divisible by k, otherwise by its negative. We then choose the k that
#   gives the maximum sum (with tie-breaking by smaller k). Finally, we
#   return (best_sum * k) mod 1e9+7.
# 
# Complexity
#   Time  : O(n * sqrt(m)) where m is the maximum value in nums, plus O(n * number_of_divisors)
#   Space : O(number_of_unique_divisors)
# 
# Runtime  : 0 ms
# Memory   : 42.6 MB
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

from typing import List

class Solution:
    def divisibleGame(self, nums: List[int]) -> int:
        MOD = 1000000007
        divisors = set()
        max_val = 0
        for x in nums:
            max_val = max(max_val, x)
            d = 1
            while d * d <= x:
                if x % d == 0:
                    if d > 1:
                        divisors.add(d)
                    e = x // d
                    if e > 1:
                        divisors.add(e)
                d += 1
        missing = 2
        while missing in divisors:
            missing += 1
        best_sum = float('-inf')
        best_k = missing
        ks = list(divisors) + [missing]
        for k in ks:
            cur_max = float('-inf')
            cur_sum = 0
            for x in nums:
                val = x if x % k == 0 else -x
                if cur_sum < 0:
                    cur_sum = val
                else:
                    cur_sum += val
                if cur_sum > cur_max:
                    cur_max = cur_sum
            if cur_max > best_sum or (cur_max == best_sum and k < best_k):
                best_sum = cur_max
                best_k = k
        ans = ((best_sum % MOD + MOD) % MOD) * best_k % MOD
        return ans
