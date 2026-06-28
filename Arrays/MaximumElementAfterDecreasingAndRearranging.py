# ──────────────────────────────────────────────────────────────────────
# LeetCode #1956 · Maximum Element After Decreasing and Rearranging
# Difficulty : Medium
# Topics     : Array, Greedy, Sorting
# URL        : https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The algorithm sorts the array first, then sets the first element to 1
#   (since it must be 1). Then, for each subsequent element, we cap it to
#   at most the previous element plus 1, because adjacent elements can
#   differ by at most 1. This greedy approach ensures we maximize the
#   final element while satisfying the constraints. The last element after
#   processing gives the maximum possible value.
# 
# Complexity
#   Time  : O(n log n)
#   Space : O(1) (excluding sorting space)
# 
# Runtime  : 1 ms
# Memory   : 43.1 MB
# 
# Examples
#   Example 1:
#     Input  : arr = [2,2,1,2,1]
#     Output : 2
#   Example 2:
#     Input  : arr = [100,1,1000]
#     Output : 3
#   Example 3:
#     Input  : arr = [1,2,3,4,5]
#     Output : 5
#     Explanation: The array already satisfies the conditions, and the largest element is 5.
# 
# Constraints
#   · 1 <= arr.length <= 105
#   · 1 <= arr[i] <= 109
# ──────────────────────────────────────────────────────────────────────

from typing import List

class Solution:
    def maximumElementAfterDecrementingAndRearranging(self, arr: List[int]) -> int:
        arr.sort()
        arr[0] = 1
        for i in range(1, len(arr)):
            arr[i] = min(arr[i], arr[i - 1] + 1)
        return arr[-1]
