/*
 * LeetCode Problem 228: Summary Ranges
 * Problem Number: 228
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/summary-ranges/
 *
 * You are given a sorted unique integer array nums.
 *
 * A range [a,b] is the set of all integers from a to b (inclusive).
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the
 * array exactly. That is, each element of nums is covered by exactly one of the
 * ranges, and there is no integer x such that x is in one of the ranges but not in
 * nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * 	"a->b" if a != b
 * 	"a" if a == b
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * Example 2:
 *
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 *
 *
 * Constraints:
 *
 * 	0 <= nums.length <= 20
 * 	-231 <= nums[i] <= 231 - 1
 * 	All the values of nums are unique.
 * 	nums is sorted in ascending order.
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 *
 * Constraints:
 * - 0 <= nums.length <= 20
 * - -231 <= nums[i] <= 231 - 1
 * - All the values of nums are
 *
 * Topics: Array
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
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
