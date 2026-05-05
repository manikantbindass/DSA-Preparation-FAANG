// LeetCode 118 - Pascal's Triangle
// Time Complexity: O(numRows^2) | Space Complexity: O(numRows^2)
import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answer = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> current = new ArrayList<>();
            current.add(1);

            for (int col = 1; col < row; col++) {
                int value = answer.get(row - 1).get(col - 1) + answer.get(row - 1).get(col);
                current.add(value);
            }

            if (row > 0) {
                current.add(1);
            }

            answer.add(current);
        }

        return answer;
    }
}
