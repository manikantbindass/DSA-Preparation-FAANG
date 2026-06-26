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
#   is "0", the entire number is zero. Otherwise, we concatenate the
#   sorted strings. This greedy sorting approach works because the
#   comparator ensures the optimal ordering.
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

def largestNumber(nums):
    # Convert to strings
    s = pd.Series(nums).astype(str)
    # Custom comparator: sort by a+b > b+a
    # We can use the key parameter with a custom function that returns a tuple
    # But pandas sort_values doesn't support custom comparator directly.
    # Instead, we can use Python's sorted with key.
    sorted_vals = sorted(s, key=lambda x: x*10, reverse=True)  # Not correct; need proper comparator
    # Proper approach: use functools.cmp_to_key
    from functools import cmp_to_key
    def compare(a, b):
        if a+b > b+a:
            return -1
        elif a+b < b+a:
            return 1
        else:
            return 0
    sorted_vals = sorted(s, key=cmp_to_key(compare))
    if sorted_vals[0] == '0':
        return '0'
    return ''.join(sorted_vals)
