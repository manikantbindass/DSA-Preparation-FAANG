// LeetCode 2033 - Minimum Operations to Make a Uni-Value Grid
// Time Complexity: O(m * n * log(m * n)) | Space Complexity: O(m * n)
import java.util.Arrays;

public class MinimumOperationsToMakeAUniValueGrid {
    public int minOperations(int[][] grid, int x) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] values = new int[rows * cols];
        int remainder = grid[0][0] % x;
        int index = 0;

        for (int[] row : grid) {
            for (int value : row) {
                if (value % x != remainder) {
                    return -1;
                }
                values[index++] = value;
            }
        }

        Arrays.sort(values);
        int median = values[values.length / 2];
        int operations = 0;

        for (int value : values) {
            operations += Math.abs(value - median) / x;
        }

        return operations;
    }
}
