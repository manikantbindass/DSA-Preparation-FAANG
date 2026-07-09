// ──────────────────────────────────────────────────────────────────────
// LeetCode #216 · Combination Sum III
// Difficulty : Medium
// Topics     : Array, Backtracking
// URL        : https://leetcode.com/problems/combination-sum-iii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use backtracking to generate all combinations of k numbers from 1
//   to 9 that sum to n. Starting from 1, we try adding each number to the
//   current combination, recursively exploring further numbers, and
//   backtrack when the combination size exceeds k or the sum exceeds n.
//   When the combination size equals k and sum equals n, we add a copy to
//   the result list. This ensures each number is used at most once and
//   combinations are unique.
// 
// Complexity
//   Time  : O(9! / (9-k)!)
//   Space : O(k)
// 
// Runtime  : 0 ms
// Memory   : 42.3 MB
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
    var backtrack func(start int, path []int, remain int)
    backtrack = func(start int, path []int, remain int) {
        if len(path) == k && remain == 0 {
            comb := make([]int, k)
            copy(comb, path)
            result = append(result, comb)
            return
        }
        if len(path) > k || remain < 0 {
            return
        }
        for i := start; i <= 9; i++ {
            path = append(path, i)
            backtrack(i+1, path, remain-i)
            path = path[:len(path)-1]
        }
    }
    backtrack(1, []int{}, n)
    return result
}
