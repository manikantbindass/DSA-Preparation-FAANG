# ──────────────────────────────────────────────────────────────────────
# LeetCode #216 · Combination Sum III
# Difficulty : Medium
# Topics     : Array, Backtracking
# URL        : https://leetcode.com/problems/combination-sum-iii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use backtracking to generate all combinations of k numbers from 1
#   to 9 that sum to n. Starting from 1, we try adding each number to the
#   current combination, recursively exploring further numbers, and
#   backtrack when the combination size exceeds k or the sum exceeds n.
#   When the combination size equals k and sum equals n, we add a copy to
#   the result list. This ensures each number is used at most once and
#   combinations are unique.
# 
# Complexity
#   Time  : O(9! / (9-k)!)
#   Space : O(k)
# 
# Runtime  : 0 ms
# Memory   : 42.3 MB
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

class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        result = []
        
        def backtrack(start: int, path: List[int], remaining: int):
            if len(path) == k and remaining == 0:
                result.append(path[:])
                return
            if len(path) > k or remaining < 0:
                return
            for i in range(start, 10):
                path.append(i)
                backtrack(i + 1, path, remaining - i)
                path.pop()
        
        backtrack(1, [], n)
        return result
