// ──────────────────────────────────────────────────────────────────────
// LeetCode #213 · House Robber II
// Difficulty : Medium
// Topics     : Array, Dynamic Programming
// URL        : https://leetcode.com/problems/house-robber-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is a circular version of House Robber. Since houses are
//   arranged in a circle, the first and last houses are adjacent, so we
//   cannot rob both. We solve this by considering two linear subproblems:
//   one excluding the last house (rob houses 0 to n-2) and one excluding
//   the first house (rob houses 1 to n-1). For each linear subproblem, we
//   use dynamic programming with two variables to track the maximum amount
//   up to the current house: 'prev2' (max up to two houses before) and
//   'prev1' (max up to previous house). At each step, the new max is
//   max(prev1, prev2 + current house value). The answer is the maximum of
//   the two subproblems.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.6 MB
// 
// Examples
//   Example 1:
//     Input  : nums = [2,3,2]
//     Output : 3
//     Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
//   Example 2:
//     Input  : nums = [1,2,3,1]
//     Output : 4
//     Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//   Example 3:
//     Input  : nums = [1,2,3]
//     Output : 3
// 
// Constraints
//   · 1 <= nums.length <= 100
//   · 0 <= nums[i] <= 1000
// ──────────────────────────────────────────────────────────────────────

func rob(nums []int) int {
    n := len(nums)
    if n == 1 {
        return nums[0]
    }
    return max(robLinear(nums, 0, n-2), robLinear(nums, 1, n-1))
}

func robLinear(nums []int, start, end int) int {
    prev2, prev1 := 0, 0
    for i := start; i <= end; i++ {
        curr := max(prev1, prev2 + nums[i])
        prev2 = prev1
        prev1 = curr
    }
    return prev1
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
