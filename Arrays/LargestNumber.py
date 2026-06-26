# ──────────────────────────────────────────────────────────────────────
# LeetCode #179 · Largest Number
# Difficulty : Medium
# Topics     : Array, String, Greedy, Sorting
# URL        : https://leetcode.com/problems/largest-number/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is to arrange a list of non-negative integers to form the
#   largest possible number. The key insight is to use a custom comparator
#   for sorting: for two strings a and b, we compare a+b vs b+a. If a+b >
#   b+a, then a should come before b. After sorting, if the first element
#   is "0", the entire number is zero. Otherwise, we join the sorted
#   strings. This greedy sorting approach works because the comparator
#   ensures the optimal order.
# 
# Complexity
#   Time  : O(n log n)
#   Space : O(n)
# 
# Runtime  : 8
# Memory   : 44964000
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        df = pd.DataFrame({'num': nums})
        df['str'] = df['num'].astype(str)
        # Custom comparator using sort_values with key is not straightforward; use Python's sort
        strs = df['str'].tolist()
        strs.sort(key=functools.cmp_to_key(lambda a, b: -1 if a + b > b + a else (1 if a + b < b + a else 0)))
        result = ''.join(strs)
        return '0' if result[0] == '0' else result
