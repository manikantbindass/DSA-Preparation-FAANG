# ──────────────────────────────────────────────────────────────────────
# LeetCode #198 · House Robber
# Difficulty : Medium
# Topics     : Array, Dynamic Programming
# URL        : https://leetcode.com/problems/house-robber/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem is solved using dynamic programming. The key insight is
#   that at each house, the robber has two choices: either rob the current
#   house and skip the next one, or skip the current house and move to the
#   next. This leads to a recurrence relation: dp[i] = max(nums[i] +
#   dp[i+2], dp[i+1]). The solution uses memoization (top-down DP) to
#   avoid redundant calculations. The base case is when i >= n, return 0.
#   The final answer is dp[0].
# 
# Complexity
#   Time  : O(n)
#   Space : O(n)
# 
# Runtime  : 0 ms
# Memory   : 42.3 MB
# 
# Examples
#   Example 1:
#     Input  : nums = [1,2,3,1]
#     Output : 4
#     Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
#   Example 2:
#     Input  : nums = [2,7,9,3,1]
#     Output : 12
#     Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
# 
# Constraints
#   · 1 <= nums.length <= 100
#   · 0 <= nums[i] <= 400
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

def rob(nums):
    """
    Calculate the maximum amount of money that can be robbed without alerting police.
    
    Parameters:
    nums (list[int]): List of money in each house
    
    Returns:
    int: Maximum amount that can be robbed
    """
    if not nums:
        return 0
    n = len(nums)
    if n == 1:
        return nums[0]
    
    # Using two variables to keep track of previous two states
    prev2 = 0  # dp[i-2]
    prev1 = nums[0]  # dp[i-1]
    
    for i in range(1, n):
        current = max(prev1, nums[i] + prev2)
        prev2 = prev1
        prev1 = current
    
    return prev1

# Example usage:
# nums = [1,2,3,1]
# print(rob(nums))  # Output: 4
