// ──────────────────────────────────────────────────────────────────────
// LeetCode #217 · Contains Duplicate
// Difficulty : Easy
// Topics     : Array, Hash Table, Sorting
// URL        : https://leetcode.com/problems/contains-duplicate/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to determine if an array contains any duplicate
//   elements. The accepted solution uses sorting: after sorting the array,
//   any duplicate elements will be adjacent. We iterate through the sorted
//   array and check if any two consecutive elements are equal. If found,
//   return true; otherwise, return false. This approach has O(n log n)
//   time due to sorting and O(1) extra space (ignoring the space used by
//   sorting).
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(1)
// 
// Runtime  : 1 ms
// Memory   : 42.8 MB
// 
// Examples
//   Example 1:
//     Input  : nums = [1,2,3,1]
//     Output : true
//   Example 2:
//     Input  : nums = [1,2,3,4]
//     Output : false
//   Example 3:
//     Input  : nums = [1,1,1,3,3,4,3,2,4,2]
//     Output : true
// 
// Constraints
//   · 1 <= nums.length <= 105
//   · -109 <= nums[i] <= 109
// ──────────────────────────────────────────────────────────────────────

import java.util.Arrays;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
