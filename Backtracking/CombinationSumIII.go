// ──────────────────────────────────────────────────────────────────────
// LeetCode #216 · Combination Sum III
// Difficulty : Medium
// Topics     : Array, Backtracking
// URL        : https://leetcode.com/problems/combination-sum-iii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use backtracking to generate all combinations of k distinct numbers
//   from 1 to 9 that sum to n. Starting from 1, we recursively add
//   numbers, ensuring we don't exceed k numbers or the target sum. When
//   the combination has exactly k numbers and sum equals n, we add it to
//   the result. We prune branches where the remaining sum is too small or
//   too large.
// 
// Complexity
//   Time  : O(9! / (9-k)!)
//   Space : O(k)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : k = 3, n = 7
//     Output : [[1,2,4]]
//   Example 2:
//     Input  : k = 3, n = 9
//     Output : [[1,2,6],[1,3,5],[2,3,4]]
//   Example 3:
//     Input  : k = 4, n = 1
//     Output : []
//     Explanation: There are no valid combinations.
// 
// Constraints
//   · 2 <= k <= 9
//   · 1 <= n <= 60
// ──────────────────────────────────────────────────────────────────────

func combinationSum3(k int, n int) [][]int {
    result := [][]int{}
    var backtrack func(start, remainingK, remainingSum int, current []int)
    backtrack = func(start, remainingK, remainingSum int, current []int) {
        if remainingK == 0 && remainingSum == 0 {
            temp := make([]int, len(current))
            copy(temp, current)
            result = append(result, temp)
            return
        }
        if remainingK == 0 || remainingSum <= 0 {
            return
        }
        for i := start; i <= 9; i++ {
            if i > remainingSum {
                break
            }
            current = append(current, i)
            backtrack(i+1, remainingK-1, remainingSum-i, current)
            current = current[:len(current)-1]
        }
    }
    backtrack(1, k, n, []int{})
    return result
}
