/*
 * LeetCode Problem 229: Majority Element II
 * Problem Number: 229
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/majority-element-ii/
 *
 * Given an integer array of size n, find all elements that appear more than
 * &lfloor;n / 3&rfloor; times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 5 * 104
 * 	-109 <= nums[i] <= 109
 *
 *
 *
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 104
 * - -109 <= nums[i] <= 109
 *
 * Topics: Array, Hash Table, Sorting, Counting
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        for (int i = 0, j,n = nums.length;i< n;i = j + 1) {
            j = i;
            while (j + 1 < n && nums[j + 1]== nums[j] + 1) {
                ++j;
            }
            ans.add(f(nums, i, j));
        }
        return ans;
    }
    private String f(int[] nums,int i, int j) {
        return i ==j ? nums[i]+ "" : String.format("%d->%d", nums[i], nums[j]);
    }
}
