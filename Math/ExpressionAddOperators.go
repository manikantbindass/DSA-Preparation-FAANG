/*
 * LeetCode Problem 282: Expression Add Operators
 * Problem Number: 282
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/expression-add-operators/
 *
 * Given a string num that contains only digits and an integer target, return all
 * possibilities to insert the binary operators '+', '-', and/or '*' between the
 * digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Note that a number can contain multiple digits.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to
 * evaluate to 9191.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= num.length <= 10
 * 	num consists of only digits.
 * 	-231 <= target <= 231 - 1
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3:
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 * - 1 <= num.length <= 10
 * - num consists of only digits.
 * - -231 <= target <= 231 - 1
 *
 * Topics: Math, String, Backtracking
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 33 ms
 * Memory: 52.9 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     private List<String> ans;
 *     private String num;
 *     private int target;
 *     public List<String> addOperators(String num, int target) {
 *         ans =new ArrayList<>();
 *         this.num= num;
 *         this.target = target;
 *         dfs(0, 0, 0, "");
 *         return ans;
 *     }
 * 
 *     private void dfs(int u, long prev, long curr, String path) {
 *         if (u == num.length()) {
 *             if (curr== target) ans.add(path);
 *             return;
 *         }
 *         for (int i=u; i < num.length(); i++) {
 *             if (i != u && num.charAt(u)== '0') {
 *                 break;
 *             }
 *             long next = Long.parseLong(num.substring(u, i + 1));
 *             if (u == 0) {
 *                 dfs(i + 1, next,next, path + next);
 *             } else {
 *                 dfs(i + 1, next, curr +next, path + "+" + next);
 *                 dfs(i + 1, -next, curr - next, path + "-" + next);
 *                 dfs(i + 1, prev* next, curr - prev + prev * next, path + "*" + next);
 *             }
 *         }
 *     }
 * }
 */

package expressionaddoperators

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
