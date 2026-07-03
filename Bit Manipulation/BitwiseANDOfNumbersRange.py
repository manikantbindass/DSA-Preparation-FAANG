# ──────────────────────────────────────────────────────────────────────
# LeetCode #201 · Bitwise AND of Numbers Range
# Difficulty : Medium
# Topics     : Bit Manipulation
# URL        : https://leetcode.com/problems/bitwise-and-of-numbers-range/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The bitwise AND of all numbers in a range [left, right] is determined
#   by the common prefix of the binary representations of left and right.
#   Any bit that changes within the range will be zero in the result. The
#   algorithm repeatedly clears the lowest set bit of right until right <=
#   left, effectively finding the common prefix. This works because
#   clearing the lowest set bit reduces the number to the next lower
#   number that shares a longer prefix with left.
# 
# Complexity
#   Time  : O(log n) where n is the maximum bit length (32 for 32-bit integers)
#   Space : O(1)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : left = 5, right = 7
#     Output : 4
#   Example 2:
#     Input  : left = 0, right = 0
#     Output : 0
#   Example 3:
#     Input  : left = 1, right = 2147483647
#     Output : 0
# 
# Constraints
#   · 0 <= left <= right <= 231 - 1
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        while left < right:
            right &= (right - 1)
        return right
