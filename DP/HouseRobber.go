// ──────────────────────────────────────────────────────────────────────
// LeetCode #198 · House Robber
// Difficulty : Medium
// Topics     : Array, Dynamic Programming
// URL        : https://leetcode.com/problems/house-robber/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   This is a classic dynamic programming problem. The key insight is that
//   for each house, you have two choices: rob it (and skip the next house)
//   or skip it (and move to the next house). The optimal solution for a
//   given starting index is the maximum of these two choices. The solution
//   uses top-down DP with memoization (DFS + cache) to avoid redundant
//   calculations. The base case is when the index exceeds the array
//   length, returning 0. The recurrence is: dp[i] = max(nums[i] + dp[i+2],
//   dp[i+1]). This can also be implemented iteratively with constant space
//   by keeping track of the last two results.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 0
// Memory   : 42928000
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func rob(nums []int) int {
    if len(nums) == 0 {
        return 0
    }
    if len(nums) == 1 {
        return nums[0]
    }
    prev2, prev1 := 0, 0
    for _, num := range nums {
        curr := max(prev1, prev2 + num)
        prev2, prev1 = prev1, curr
    }
    return prev1
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
