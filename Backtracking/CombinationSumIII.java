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

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int k, int remaining, List<Integer> current, List<List<Integer>> result) {
        if (k == 0 && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (k == 0 || remaining <= 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i > remaining) break;
            current.add(i);
            backtrack(i + 1, k - 1, remaining - i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
