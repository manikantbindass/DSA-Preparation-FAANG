# ──────────────────────────────────────────────────────────────────────
# LeetCode #3299 · Find the Maximum Number of Elements in Subset
# Difficulty : Medium
# Topics     : Array, Hash Table, Enumeration
# URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We need to find the longest subset that can be arranged into a
#   palindrome-like sequence where each element is the square of the
#   previous one until the middle, then mirrored. This is essentially a
#   chain of numbers where each step squares the previous number. We count
#   frequencies of each number. For 1, special handling: since 1^2 = 1, we
#   can have any number of 1s, but the pattern must be symmetric, so we
#   can take at most an odd number of 1s (the maximum odd ≤ count). For
#   other numbers, we start from a number and repeatedly square it while
#   we have at least two copies of the current number (to form both sides
#   of the palindrome). When we can't square further, we add one copy of
#   the final number if available (as the middle element). We track the
#   maximum length found.
# 
# Complexity
#   Time  : O(n log log max)
#   Space : O(n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : nums = [5,4,1,2,2]
#     Output : 3
#     Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.
#   Example 2:
#     Input  : nums = [1,3,2,4]
#     Output : 1
#     Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer.
# 
# Constraints
#   · 2 <= nums.length <= 105
#   · 1 <= nums[i] <= 109
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        from collections import Counter
        cnt = Counter(nums)
        # handle 1 specially
        ones = cnt.pop(1, 0)
        ans = ones if ones % 2 == 1 else ones - 1 if ones > 0 else 0
        for x in list(cnt.keys()):
            length = 0
            cur = x
            while cnt.get(cur, 0) >= 2:
                length += 2
                cur = cur * cur
            length += 1 if cnt.get(cur, 0) == 1 else 0
            ans = max(ans, length)
        return ans
