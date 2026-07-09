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

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> temp, int k, int remain, int start) {
        if (temp.size() == k && remain == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() > k || remain < 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            temp.add(i);
            backtrack(result, temp, k, remain - i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
