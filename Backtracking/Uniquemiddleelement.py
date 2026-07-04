# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · unique-middle-element
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/unique-middle-element/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The algorithm checks if the middle element of the array is unique. It
#   first identifies the middle element using integer division
#   (nums.length / 2). Then it counts how many times that element appears
#   in the entire array. If the count is exactly 1, the middle element is
#   unique; otherwise, it is not. This approach runs in O(n) time and uses
#   O(1) extra space.
# 
# Complexity
#   Time  : O(n)
#   Space : O(1)
# 
# Runtime  : 1 ms
# Memory   : 46.5 MB
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def isMiddleElementUnique(self, nums: List[int]) -> bool:
        mid = nums[len(nums) // 2]
        count = sum(1 for num in nums if num == mid)
        return count == 1
