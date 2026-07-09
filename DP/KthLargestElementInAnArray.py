# ──────────────────────────────────────────────────────────────────────
# LeetCode #215 · Kth Largest Element in an Array
# Difficulty : Medium
# Topics     : Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
# URL        : https://leetcode.com/problems/kth-largest-element-in-an-array/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use the Quickselect algorithm, which is a selection algorithm to
#   find the kth smallest element in an average O(n) time. Since we need
#   the kth largest, we convert k to the index of the kth smallest
#   (nums.length - k). We partition the array around a pivot
#   (median-of-three or random) and recursively search only the side that
#   contains the target index. This avoids sorting the entire array.
# 
# Complexity
#   Time  : O(n) average, O(n^2) worst-case
#   Space : O(log n) average recursion depth
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : nums = [3,2,1,5,6,4], k = 2
#     Output : 5
#   Example 2:
#     Input  : nums = [3,2,3,1,2,4,5,5,6], k = 4
#     Output : 4
# 
# Constraints
#   · 1 <= k <= nums.length <= 105
#   · -104 <= nums[i] <= 104
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        target = len(nums) - k
        return self.quick_select(nums, 0, len(nums) - 1, target)
    
    def quick_select(self, nums: List[int], left: int, right: int, target: int) -> int:
        if left == right:
            return nums[left]
        pivot_index = self.partition(nums, left, right)
        if target == pivot_index:
            return nums[target]
        elif target < pivot_index:
            return self.quick_select(nums, left, pivot_index - 1, target)
        else:
            return self.quick_select(nums, pivot_index + 1, right, target)
    
    def partition(self, nums: List[int], left: int, right: int) -> int:
        pivot = nums[right]
        i = left
        for j in range(left, right):
            if nums[j] <= pivot:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
        nums[i], nums[right] = nums[right], nums[i]
        return i
