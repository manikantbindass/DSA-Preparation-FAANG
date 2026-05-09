// LeetCode 1914 - Cyclically Rotating a Grid
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
import java.util.ArrayList;
import java.util.List;

public class CyclicallyRotatingAGrid {
    private int rows;
    private int cols;
    private int[][] grid;

    public int[][] rotateGrid(int[][] grid, int k) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;

        for (int layer = 0; layer < Math.min(rows, cols) / 2; layer++) {
            rotateLayer(layer, k);
        }

        return grid;
    }

    private void rotateLayer(int layer, int k) {
        List<Integer> values = new ArrayList<>();

        for (int col = layer; col < cols - layer - 1; col++) {
            values.add(grid[layer][col]);
        }
        for (int row = layer; row < rows - layer - 1; row++) {
            values.add(grid[row][cols - layer - 1]);
        }
        for (int col = cols - layer - 1; col > layer; col--) {
            values.add(grid[rows - layer - 1][col]);
        }
        for (int row = rows - layer - 1; row > layer; row--) {
            values.add(grid[row][layer]);
        }

        int length = values.size();
        k %= length;
        if (k == 0) {
            return;
        }

        for (int col = layer; col < cols - layer - 1; col++) {
            grid[layer][col] = values.get(k++ % length);
        }
        for (int row = layer; row < rows - layer - 1; row++) {
            grid[row][cols - layer - 1] = values.get(k++ % length);
        }
        for (int col = cols - layer - 1; col > layer; col--) {
            grid[rows - layer - 1][col] = values.get(k++ % length);
        }
        for (int row = rows - layer - 1; row > layer; row--) {
            grid[row][layer] = values.get(k++ % length);
        }
    }
}
