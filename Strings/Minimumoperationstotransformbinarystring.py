# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · minimum-operations-to-transform-binary-string
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/minimum-operations-to-transform-binary-string/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is to find the maximum sum of two numbers in an array such
#   that their indices differ by at least k. The solution uses a single
#   pass: maintain the maximum value seen so far among the first j-k
#   elements, then for each element at index j (starting from k), compute
#   the sum of that element and the current maximum from the left side,
#   updating the answer. This ensures the indices differ by at least k.
#   The algorithm runs in O(n) time and O(1) space.
# 
# Complexity
#   Time  : O(n)
#   Space : O(1)
# 
# Runtime  : 0 ms
# Memory   : 42.9 MB
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def maxValidPairSum(self, nums: List[int], k: int) -> int:
        max_left = nums[0]
        ans = float('-inf')
        for j in range(k, len(nums)):
            max_left = max(max_left, nums[j - k])
            ans = max(ans, max_left + nums[j])
        return ans
