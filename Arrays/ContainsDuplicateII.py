# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · Contains Duplicate II
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/contains-duplicate-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use a hashmap to store the most recent index of each number. As we
#   iterate through the array, we check if the current number has been
#   seen before and if the difference between the current index and the
#   stored index is at most k. If so, we return true. Otherwise, we update
#   the map with the current index. This ensures we only need one pass
#   through the array.
# 
# Complexity
#   Time  : O(n)
#   Space : O(n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def containsNearbyDuplicate(self, nums: list[int], k: int) -> bool:
        seen = {}
        for i, num in enumerate(nums):
            if num in seen and i - seen[num] <= k:
                return True
            seen[num] = i
        return False
