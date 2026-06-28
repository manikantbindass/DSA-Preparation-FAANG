# ──────────────────────────────────────────────────────────────────────
# LeetCode #189 · Rotate Array
# Difficulty : Medium
# Topics     : Array, Math, Two Pointers
# URL        : https://leetcode.com/problems/rotate-array/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The solution uses the reverse method to rotate the array in-place with
#   O(1) extra space. First, we normalize k by taking modulo n (array
#   length) to handle cases where k >= n. Then we reverse the entire
#   array, which moves the last k elements to the front but in reversed
#   order. Next, we reverse the first k elements to restore their original
#   order, and finally reverse the remaining n-k elements to restore their
#   order. This results in the array being rotated right by k steps.
# 
# Complexity
#   Time  : O(n)
#   Space : O(1)
# 
# Runtime  : 0 ms
# Memory   : 43 MB
# 
# Examples
#   Example 1:
#     Input  : nums = [1,2,3,4,5,6,7], k = 3
#     Output : [5,6,7,1,2,3,4]
#   Example 2:
#     Input  : nums = [-1,-100,3,99], k = 2
#     Output : [3,99,-1,-100]
# 
# Constraints
#   · 1 <= nums.length <= 105
#   · -231 <= nums[i] <= 231 - 1
#   · 0 <= k <= 105
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        k %= n
        self._reverse(nums, 0, n - 1)
        self._reverse(nums, 0, k - 1)
        self._reverse(nums, k, n - 1)

    def _reverse(self, nums: List[int], i: int, j: int) -> None:
        while i < j:
            nums[i], nums[j] = nums[j], nums[i]
            i += 1
            j -= 1
