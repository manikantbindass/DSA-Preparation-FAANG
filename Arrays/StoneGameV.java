/*
 * LeetCode Problem 1685: Stone Game V
 * Problem Number: 1685
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/stone-game-v/
 *
 * There are several stones arranged in a row, and each stone has an associated
 * value which is an integer given in the array stoneValue.
 *
 * In each round of the game, Alice divides the row into two non-empty rows (i.e.
 * left row and right row), then Bob calculates the value of each row which is the
 * sum of the values of all the stones in this row. Bob throws away the row which
 * has the maximum value, and Alice's score increases by the value of the remaining
 * row. If the value of the two rows are equal, Bob lets Alice decide which row
 * will be thrown away. The next round starts with the remaining row.
 *
 * The game ends when there is only one stone remaining. Alice's score is initially
 * zero.
 *
 * Return the maximum score that Alice can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The
 * left row has the value 11 and the right row has value 14. Bob throws away the
 * right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws
 * away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3].
 * Bob throws away the right row and Alice's score is now 18 (16 + 2). The game
 * ends because only one stone is remaining in the row.
 *
 * Example 2:
 *
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 *
 * Example 3:
 *
 * Input: stoneValue = [4]
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 	1 <= stoneValue.length <= 500
 * 	1 <= stoneValue[i] <= 106
 *
 * Example 1:
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
 *
 * Example 2:
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 *
 * Example 3:
 * Input: stoneValue = [4]
 * Output: 0
 *
 * Constraints:
 * - 1 <= stoneValue.length <= 500
 * - 1 <= stoneValue[i] <= 106
 *
 * Topics: Array, Math, Dynamic Programming, Game Theory
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 0 ms
 * Memory: 42.4 MB
 */

class Solution {
    private int n;
    private int[] s;
    private int[] nums;
    private Integer[][] f;
    public int stoneGameV(int[] stoneValue) {
        n= stoneValue.length;
        s =new int[n + 1];
        nums = stoneValue;
        f = new Integer[n][n];
        for (int i=1; i <= n; ++i) {
            s[i] =s[i - 1] + nums[i - 1];
        }
        return dfs(0, n- 1);
    }
    private int dfs(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0, l=0, r= s[j + 1] - s[i];
        for (int k = i; k < j; ++k) {
            l+=nums[k];
            r-= nums[k];
            if (l < r) {
                if (ans > l * 2) {
                    continue;
                }
                ans=Math.max(ans, l + dfs(i, k));
            } else if (l > r) {
                if (ans > r * 2) {
                    break;
                }
                ans =Math.max(ans, r + dfs(k + 1, j));
            } else {
                ans=Math.max(ans, Math.max(l + dfs(i, k), r + dfs(k + 1, j)));
            }
        }
        return f[i][j]= ans;
    }
}
