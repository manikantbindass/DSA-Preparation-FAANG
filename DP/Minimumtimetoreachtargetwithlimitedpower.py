# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · minimum-time-to-reach-target-with-limited-power
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/minimum-time-to-reach-target-with-limited-power/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is to find the maximum subarray sum after optionally
#   multiplying or dividing one element by k. The solution uses prefix and
#   suffix maximum subarray sums (Kadane's algorithm) to compute the best
#   subarray that includes a modified element. For each index i, we
#   consider two cases: multiply nums[i] by k or divide by k (integer
#   division). We compute the best subarray ending at i (left side) and
#   starting at i (right side), then combine them. The answer is the
#   maximum over all i and both operations.
# 
# Complexity
#   Time  : O(n)
#   Space : O(n)
# 
# Runtime  : 1 ms
# Memory   : 42.7 MB
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def maxSubarraySum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        suf = [0] * n
        pre = [0] * n
        suf[0] = nums[0]
        for i in range(1, n):
            suf[i] = max(nums[i], suf[i-1] + nums[i])
        pre[n-1] = nums[n-1]
        for i in range(n-2, -1, -1):
            pre[i] = max(nums[i], pre[i+1] + nums[i])
        lft = [max(0, suf[i]) for i in range(n)]
        rgt = [max(0, pre[i]) for i in range(n)]
        def best(mul):
            top = -10**18
            cur = 0
            for i in range(n):
                val = nums[i] * k if mul else nums[i] // k
                ext = 0 if i == 0 else max(cur, lft[i-1])
                cur = val + ext
                tail = rgt[i+1] if i+1 < n else 0
                top = max(top, cur + tail)
            return top
        return max(best(True), best(False))
