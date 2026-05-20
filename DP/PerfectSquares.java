// LeetCode 279 - Perfect Squares
// Time Complexity: O(n * sqrt(n)) | Space Complexity: O(n)
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        for (int value = 1; value <= n; value++) {
            dp[value] = value;
            for (int square = 1; square * square <= value; square++) {
                dp[value] = Math.min(dp[value], dp[value - square * square] + 1);
            }
        }

        return dp[n];
    }
}
