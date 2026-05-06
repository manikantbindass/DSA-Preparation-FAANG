// LeetCode 119 - Pascal's Triangle II
// Time Complexity: O(rowIndex^2) | Space Complexity: O(rowIndex)
import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        for (int index = 0; index <= rowIndex; index++) {
            row.add(1);
        }

        for (int currentRow = 2; currentRow <= rowIndex; currentRow++) {
            for (int col = currentRow - 1; col > 0; col--) {
                row.set(col, row.get(col) + row.get(col - 1));
            }
        }

        return row;
    }
}
