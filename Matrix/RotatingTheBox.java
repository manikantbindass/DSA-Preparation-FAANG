// LeetCode 1861 - Rotating the Box
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
import java.util.ArrayDeque;
import java.util.Deque;

public class RotatingTheBox {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int rows = boxGrid.length;
        int cols = boxGrid[0].length;
        char[][] rotated = new char[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotated[col][rows - row - 1] = boxGrid[row][col];
            }
        }

        for (int col = 0; col < rows; col++) {
            Deque<Integer> emptyRows = new ArrayDeque<>();

            for (int row = cols - 1; row >= 0; row--) {
                if (rotated[row][col] == '*') {
                    emptyRows.clear();
                } else if (rotated[row][col] == '.') {
                    emptyRows.offerLast(row);
                } else if (!emptyRows.isEmpty()) {
                    rotated[emptyRows.pollFirst()][col] = '#';
                    rotated[row][col] = '.';
                    emptyRows.offerLast(row);
                }
            }
        }

        return rotated;
    }
}
