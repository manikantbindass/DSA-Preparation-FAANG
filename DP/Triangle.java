// LeetCode 120 - Triangle
// Time Complexity: O(n^2) | Space Complexity: O(n^2)
import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];

        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[row][col] = Math.min(dp[row + 1][col], dp[row + 1][col + 1]) + triangle.get(row).get(col);
            }
        }

        return dp[0][0];
    }
}
