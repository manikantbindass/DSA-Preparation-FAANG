/*
 * LeetCode Problem 283: Move Zeroes
 * Problem Number: 283
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/move-zeroes/
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining the
 * relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 104
 * 	-231 <= nums[i] <= 231 - 1
 *
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 * - 1 <= nums.length <= 104
 * - -231 <= nums[i] <= 231 - 1
 *
 * Topics: Array, Two Pointers
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    private List<String> ans;
    private String num;
    private int target;
    public List<String> addOperators(String num, int target) {
        ans =new ArrayList<>();
        this.num= num;
        this.target = target;
        dfs(0, 0, 0, "");
        return ans;
    }

    private void dfs(int u, long prev, long curr, String path) {
        if (u == num.length()) {
            if (curr== target) ans.add(path);
            return;
        }
        for (int i=u; i < num.length(); i++) {
            if (i != u && num.charAt(u)== '0') {
                break;
            }
            long next = Long.parseLong(num.substring(u, i + 1));
            if (u == 0) {
                dfs(i + 1, next,next, path + next);
            } else {
                dfs(i + 1, next, curr +next, path + "+" + next);
                dfs(i + 1, -next, curr - next, path + "-" + next);
                dfs(i + 1, prev* next, curr - prev + prev * next, path + "*" + next);
            }
        }
    }
}
