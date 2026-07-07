# ──────────────────────────────────────────────────────────────────────
# LeetCode #4135 · Concatenate Non-Zero Digits and Multiply by Sum I
# Difficulty : Easy
# Topics     : Math
# URL        : https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We iterate through the digits of n from least significant to most
#   significant. For each non-zero digit, we add it to the sum and also
#   build the number x by placing the digit at the correct position (using
#   a multiplier p that increases by a factor of 10 for each non-zero
#   digit encountered). Finally, we return the product of x and the sum.
# 
# Complexity
#   Time  : O(log n)
#   Space : O(1)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : n = 10203004
#     Output : 12340
#   Example 2:
#     Input  : n = 1000
#     Output : 1
# 
# Constraints
#   · 0 <= n <= 109
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def sumAndMultiply(self, n: int) -> int:
        p = 1
        x = 0
        s = 0
        while n > 0:
            v = n % 10
            if v != 0:
                s += v
                x += p * v
                p *= 10
            n //= 10
        return x * s
