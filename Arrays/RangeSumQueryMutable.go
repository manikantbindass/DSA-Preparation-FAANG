/*
 * LeetCode Problem 307: Range Sum Query - Mutable
 * Problem Number: 307
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/range-sum-query-mutable/
 *
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * 	Update the value of an element in nums.
 * 	Calculate the sum of the elements of nums between indices left and right
 * inclusive where left <= right.
 *
 * Implement the NumArray class:
 *
 * 	NumArray(int[] nums) Initializes the object with the integer array nums.
 * 	void update(int index, int val) Updates the value of nums[index] to be val.
 * 	int sumRange(int left, int right) Returns the sum of the elements of nums
 * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ...
 * + nums[right]).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 3 * 104
 * 	-100 <= nums[i] <= 100
 * 	0 <= index < nums.length
 * 	-100 <= val <= 100
 * 	0 <= left <= right < nums.length
 * 	At most 3 * 104 calls will be made to update and sumRange.
 *
 * Example 1:
 * Input: ["NumArray","sumRange","update","sumRange"]
 * Output: [[[1,3,5]],[0,2],[1,2],[0,2]]
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 104
 * - -100 <= nums[i] <= 100
 * - 0 <= index < nums.length
 * - -100 <= val <= 100
 * - 0 <= left <= right < nums.length
 * - At most 3 * 104 calls will be made to update and sumRange.
 *
 * Topics: Array, Divide and Conquer, Design, Binary Indexed Tree, Segment Tree, Sqrt Decomposition
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     public boolean isAdditiveNumber(String num) {
 *         int n = num.length();
 *         for (int i =1; i < Math.min(n - 1, 19);++i) {
 *             for (int j=i+1; j < Math.min(n, i+ 19);++j) {
 *                 if (i >1 && num.charAt(0) =='0') {
 *                     break;
 *                 }
 *                 if (j-i >1 && num.charAt(i)== '0') {
 *                     continue;
 *                 }
 *                 long a= Long.parseLong(num.substring(0, i));
 *                 long b =Long.parseLong(num.substring(i, j));
 *                 if (dfs(a, b, num.substring(j))) {
 *                     return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 *     private boolean dfs(long a, long b, String num) {
 *         if ("".equals(num)) {
 *             return true;
 *         }
 *         if (a+b>0 && num.charAt(0) =='0') {
 *             return false;
 *         }
 *         for (int i= 1; i < Math.min(num.length() + 1,19);++i) {
 *             if (a+b== Long.parseLong(num.substring(0,i))) {
 *                 if (dfs(b,a + b,num.substring(i))) {
 *                     return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 * }
 */

package rangesumquerymutable

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
