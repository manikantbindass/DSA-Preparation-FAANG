# ──────────────────────────────────────────────────────────────────────
# LeetCode #0 · Contains Duplicate III
# Difficulty : Medium
# Topics     : N/A
# URL        : https://leetcode.com/problems/contains-duplicate-iii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use a sliding window of size indexDiff and maintain a balanced BST
#   (TreeSet in Java, sortedcontainers in Python, or a custom approach in
#   Go) to store the numbers in the current window. For each new number,
#   we check if there exists a number in the set that is within valueDiff
#   of the current number. Specifically, we find the smallest number >=
#   (nums[i] - valueDiff) and check if it is <= (nums[i] + valueDiff). If
#   such a number exists, we return true. Otherwise, we add the current
#   number to the set and remove the number that is indexDiff positions
#   behind (if the window size exceeds indexDiff). This ensures we only
#   consider pairs within the allowed index difference. The balanced BST
#   allows O(log k) operations where k is the window size.
# 
# Complexity
#   Time  : O(n log k) where k = indexDiff
#   Space : O(k)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

from sortedcontainers import SortedList

class Solution:
    def containsNearbyAlmostDuplicate(self, nums: List[int], indexDiff: int, valueDiff: int) -> bool:
        window = SortedList()
        for i, num in enumerate(nums):
            # Find the smallest number >= num - valueDiff
            pos = window.bisect_left(num - valueDiff)
            if pos < len(window) and window[pos] <= num + valueDiff:
                return True
            window.add(num)
            if i >= indexDiff:
                window.remove(nums[i - indexDiff])
        return False
