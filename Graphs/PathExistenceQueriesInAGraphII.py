# ──────────────────────────────────────────────────────────────────────
# LeetCode #3852 · Path Existence Queries in a Graph II
# Difficulty : Hard
# Topics     : Array, Two Pointers, Binary Search, Dynamic Programming, Greedy, Bit Manipulation, Graph Theory, Sorting
# URL        : https://leetcode.com/problems/path-existence-queries-in-a-graph-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We sort the nodes by their nums values. For each node (in decreasing
#   order of nums), we find the farthest node to the right (larger nums)
#   such that the difference is ≤ maxDiff. This gives a directed edge from
#   the smaller nums node to the larger nums node. Using binary lifting
#   (up to 20 levels because n ≤ 1e5), we precompute ancestors. For each
#   query, we ensure ui has smaller nums (swap if needed). If equal nums,
#   answer is 1 if they are different (since edge exists if difference ≤
#   maxDiff, and 0 ≤ maxDiff, so equal nums always have an edge).
#   Otherwise, we try to jump from ui to a node with nums just below vj's
#   nums. If the next jump would exceed vj's nums, we stop. Then check if
#   the direct neighbor reaches vj; if yes, answer is steps+1; else -1.
# 
# Complexity
#   Time  : O((n + q) log n)
#   Space : O(n log n)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
#     Output : [1,1]
#   Example 2:
#     Input  : n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]
#     Output : [1,2,-1,1]
#   Example 3:
#     Input  : n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]
#     Output : [0,-1,-1]
# 
# Constraints
#   · 1 <= n == nums.length <= 105
#   · 0 <= nums[i] <= 105
#   · 0 <= maxDiff <= 105
#   · 1 <= queries.length <= 105
#   · queries[i] == [ui, vi]
#   · 0 <= ui, vi < n
# ──────────────────────────────────────────────────────────────────────

from typing import List

class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[int]:
        # Pair each node with its nums value
        pairs = [(nums[i], i) for i in range(n)]
        pairs.sort(key=lambda x: x[0])
        
        m = 20  # log2(1e5) ≈ 17, use 20 for safety
        up = [[0] * m for _ in range(n)]
        r = n - 1
        for l in range(n - 1, -1, -1):
            while pairs[r][0] - pairs[l][0] > maxDiff:
                r -= 1
            i = pairs[l][1]
            j = pairs[r][1]
            up[i][0] = j
            for k in range(1, m):
                up[i][k] = up[up[i][k-1]][k-1]
        
        ans = []
        for u, v in queries:
            if nums[u] > nums[v]:
                u, v = v, u
            if u == v:
                ans.append(0)
                continue
            if nums[u] == nums[v]:
                ans.append(1)
                continue
            steps = 0
            cur = u
            for k in range(m - 1, -1, -1):
                if nums[up[cur][k]] < nums[v]:
                    steps |= (1 << k)
                    cur = up[cur][k]
            if nums[up[cur][0]] < nums[v]:
                ans.append(-1)
            else:
                ans.append(steps + 1)
        return ans
