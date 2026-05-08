// LeetCode 52 - N-Queens II
// Time Complexity: O(n!) | Space Complexity: O(n)
public class NQueensII {
    private int size;
    private int answer;
    private final boolean[] columns = new boolean[10];
    private final boolean[] diagonals = new boolean[20];
    private final boolean[] antiDiagonals = new boolean[20];

    public int totalNQueens(int n) {
        size = n;
        answer = 0;
        backtrack(0);
        return answer;
    }

    private void backtrack(int row) {
        if (row == size) {
            answer++;
            return;
        }

        for (int col = 0; col < size; col++) {
            int diagonalIndex = row + col;
            int antiDiagonalIndex = row - col + size;
            if (columns[col] || diagonals[diagonalIndex] || antiDiagonals[antiDiagonalIndex]) {
                continue;
            }

            columns[col] = true;
            diagonals[diagonalIndex] = true;
            antiDiagonals[antiDiagonalIndex] = true;
            backtrack(row + 1);
            columns[col] = false;
            diagonals[diagonalIndex] = false;
            antiDiagonals[antiDiagonalIndex] = false;
        }
    }
}
