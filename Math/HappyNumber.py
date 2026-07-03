# ──────────────────────────────────────────────────────────────────────
# LeetCode #202 · Happy Number
# Difficulty : Easy
# Topics     : Hash Table, Math, Two Pointers
# URL        : https://leetcode.com/problems/happy-number/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is to determine if a number is a happy number. A happy
#   number eventually reaches 1 when repeatedly replaced by the sum of the
#   squares of its digits; otherwise, it enters a cycle that does not
#   include 1. The solution uses Floyd's cycle detection algorithm (two
#   pointers: slow and fast) to detect cycles without extra space. The
#   slow pointer moves one step (computes sum of squares once), and the
#   fast pointer moves two steps (computes sum of squares twice). If they
#   meet at 1, the number is happy; if they meet at any other number, a
#   cycle exists and the number is not happy. This approach is efficient
#   with O(log n) time per step and O(1) space.
# 
# Complexity
#   Time  : O(log n) per step, overall O(log n) due to cycle detection
#   Space : O(1)
# 
# Runtime  : 0 ms
# Memory   : 42 MB
# 
# Examples
#   Example 1:
#     Input  : n = 19
#     Output : true
#   Example 2:
#     Input  : n = 2
#     Output : false
# 
# Constraints
#   · 1 <= n <= 231 - 1
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def isHappy(self, n: int) -> bool:
        def get_next(num: int) -> int:
            total = 0
            while num > 0:
                digit = num % 10
                total += digit * digit
                num //= 10
            return total
        
        slow = n
        fast = get_next(n)
        while fast != 1 and slow != fast:
            slow = get_next(slow)
            fast = get_next(get_next(fast))
        return fast == 1
