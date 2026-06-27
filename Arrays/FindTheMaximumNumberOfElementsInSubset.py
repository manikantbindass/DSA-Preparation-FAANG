# ──────────────────────────────────────────────────────────────────────
# LeetCode #3299 · Find the Maximum Number of Elements in Subset
# Difficulty : Medium
# Topics     : Array, Hash Table, Enumeration
# URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We need to find the longest subset that can be arranged into a
#   palindrome-like sequence where each element is the square of the
#   previous one, except the middle element which appears once (or twice
#   if the sequence length is even). The pattern is symmetric: [x, x^2,
#   x^4, ..., x^(2^k), ..., x^4, x^2, x]. This means we can think of
#   building chains by repeatedly squaring numbers. For each starting
#   number, we can follow the chain as long as we have at least two copies
#   of each intermediate number (except possibly the last one which can
#   have one copy). Special case: number 1, because 1^2 = 1, so any number
#   of 1's can form a chain of length equal to the count of 1's, but the
#   pattern requires symmetry: if count is odd, we can use all; if even,
#   we can use all but one (since the middle element appears once). We use
#   a frequency map. For each distinct number (except 1), we traverse the
#   chain: while the current number appears at least twice, we move to its
#   square and add 2 to the length. At the end, if the final number
#   appears at least once, we add 1. We track the maximum length. For 1,
#   we handle separately: if count is odd, answer = count; if even, answer
#   = count - 1 (since we need odd length for palindrome).
# 
# Complexity
#   Time  : O(n log log maxVal)
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

from collections import Counter

class Solution:
    def maximumLength(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        # handle 1 separately
        ones = cnt.pop(1, 0)
        ans = ones if ones % 2 == 1 else ones - 1 if ones > 0 else 0
        for x in list(cnt.keys()):
            length = 0
            while cnt.get(x, 0) > 1:
                x = x * x
                length += 2
            length += cnt.get(x, -1)
            ans = max(ans, length)
        return ans
