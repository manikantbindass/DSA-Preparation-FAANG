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

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
