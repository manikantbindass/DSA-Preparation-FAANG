// LeetCode 1559 - Detect Cycles in 2D Grid
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
public class DetectCyclesIn2DGrid {
    private char[][] grid;
    private boolean[][] visited;
    private final int[] directions = {-1, 0, 1, 0, -1};
    private int rows;
    private int cols;

    public boolean containsCycle(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col] && dfs(row, col, -1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int col, int parentRow, int parentCol) {
        visited[row][col] = true;

        for (int index = 0; index < 4; index++) {
            int nextRow = row + directions[index];
            int nextCol = col + directions[index + 1];

            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                continue;
            }

            if (grid[nextRow][nextCol] != grid[row][col]
                    || (nextRow == parentRow && nextCol == parentCol)) {
                continue;
            }

            if (visited[nextRow][nextCol] || dfs(nextRow, nextCol, row, col)) {
                return true;
            }
        }

        return false;
    }
}
