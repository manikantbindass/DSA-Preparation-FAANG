# ──────────────────────────────────────────────────────────────────────
# LeetCode #3299 · Find the Maximum Number of Elements in Subset
# Difficulty : Medium
# Topics     : Array, Hash Table, Enumeration
# URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We need to find the longest subsequence that can be arranged into a
#   palindrome-like pattern where each element is the square of the
#   previous one until the middle, then mirrored. The pattern is
#   symmetric: starting from a base x, then x^2, x^4, ..., up to some
#   peak, then back down. This means the sequence is determined by
#   repeatedly squaring a starting number until we reach a number that
#   appears only once (the peak) or until we cannot square further. The
#   count of each number matters: for numbers that appear at least twice,
#   we can use them in pairs (one on each side of the peak). The peak
#   itself can be used only once. Special case: number 1, because 1^2 = 1,
#   so any number of 1's can form a sequence of odd length (all 1's). We
#   handle 1 separately: the best we can do with 1's is the largest odd
#   number ≤ count(1). For other numbers, we iterate over each distinct
#   number as a potential start, repeatedly square it while the count of
#   the current number is at least 2, adding 2 to the length each time.
#   When we hit a number that appears only once or doesn't exist, we stop.
#   If the final number exists (count >= 1), we add 1 for the peak. We
#   track the maximum length found. This approach uses a hash map for
#   counts and processes each number's chain only once (since squaring
#   quickly exceeds 1e9, the chain length is small). Complexity: O(n log
#   max) time, O(n) space.
# 
# Complexity
#   Time  : O(n log M) where M is max value (due to squaring chain length bounded by log log M)
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
        # handle 1 separately
        ones = cnt.pop(1, 0)
        ans = ones if ones % 2 == 1 else ones - 1
        for x in list(cnt.keys()):
            length = 0
            cur = x
            while cnt.get(cur, 0) >= 2:
                length += 2
                cur = cur * cur
            length += 1 if cnt.get(cur, 0) >= 1 else -1
            ans = max(ans, length)
        return ans
