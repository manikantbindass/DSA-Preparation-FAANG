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
#   essentially a sequence that starts with some number x, then repeatedly
#   squares until reaching a peak, then mirrors back. The length of such a
#   sequence is 2k+1 where k is the number of squaring steps (including
#   the middle element counted once). For a given starting number x, we
#   can build the sequence by repeatedly squaring as long as we have at
#   least two copies of each intermediate number (except possibly the peak
#   which needs only one copy). Special case: number 1, because 1^2 = 1,
#   so we can have any number of 1's, but the pattern requires that the
#   sequence be symmetric; with 1's we can form a sequence of length
#   (count of 1's) if count is odd, or count-1 if even (since we need a
#   single middle element). We use a hashmap to count frequencies, then
#   for each distinct number (except 1) we try to build the longest chain
#   by repeatedly squaring while we have at least 2 copies, and finally
#   add 1 if the peak exists. We track the maximum length. For 1, we
#   handle separately as described.
# 
# Complexity
#   Time  : O(n log M) where M is max value, due to repeated squaring
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
        cnt = {}
        for x in nums:
            cnt[x] = cnt.get(x, 0) + 1
        # handle 1 separately
        ones = cnt.pop(1, 0)
        ans = ones if ones % 2 == 1 else ones - 1 if ones > 0 else 0
        for x in list(cnt.keys()):
            length = 0
            cur = x
            while cnt.get(cur, 0) >= 2:
                length += 2
                cur = cur * cur
            length += cnt.get(cur, -1)
            ans = max(ans, length)
        return ans
