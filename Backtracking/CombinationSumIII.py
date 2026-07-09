# ──────────────────────────────────────────────────────────────────────
# LeetCode #216 · Combination Sum III
# Difficulty : Medium
# Topics     : Array, Backtracking
# URL        : https://leetcode.com/problems/combination-sum-iii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use backtracking to generate all combinations of k distinct numbers
#   from 1 to 9 that sum to n. Starting from 1, we recursively add
#   numbers, ensuring we don't exceed k numbers or the target sum. When
#   the combination has exactly k numbers and sum equals n, we add it to
#   the result. We prune branches where the remaining sum is too small or
#   too large.
# 
# Complexity
#   Time  : O(9! / (9-k)!)
#   Space : O(k)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : k = 3, n = 7
#     Output : [[1,2,4]]
#   Example 2:
#     Input  : k = 3, n = 9
#     Output : [[1,2,6],[1,3,5],[2,3,4]]
#   Example 3:
#     Input  : k = 4, n = 1
#     Output : []
#     Explanation: There are no valid combinations.
# 
# Constraints
#   · 2 <= k <= 9
#   · 1 <= n <= 60
# ──────────────────────────────────────────────────────────────────────

from typing import List

class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        result = []
        
        def backtrack(start: int, remaining_k: int, remaining_sum: int, current: List[int]):
            if remaining_k == 0 and remaining_sum == 0:
                result.append(current[:])
                return
            if remaining_k == 0 or remaining_sum <= 0:
                return
            for i in range(start, 10):
                if i > remaining_sum:
                    break
                current.append(i)
                backtrack(i + 1, remaining_k - 1, remaining_sum - i, current)
                current.pop()
        
        backtrack(1, k, n, [])
        return result
