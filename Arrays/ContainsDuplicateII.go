// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · Contains Duplicate II
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/contains-duplicate-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a hashmap to store the most recent index of each number. As we
//   iterate through the array, we check if the current number has been
//   seen before and if the difference between the current index and the
//   stored index is at most k. If so, we return true. Otherwise, we update
//   the map with the current index. This ensures we only need one pass
//   through the array.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func containsNearbyDuplicate(nums []int, k int) bool {
    seen := make(map[int]int)
    for i, num := range nums {
        if idx, ok := seen[num]; ok && i-idx <= k {
            return true
        }
        seen[num] = i
    }
    return false
}
