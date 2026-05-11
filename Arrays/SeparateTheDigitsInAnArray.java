// LeetCode 2553 - Separate the Digits in an Array
// Time Complexity: O(n * d) | Space Complexity: O(n * d)
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeparateTheDigitsInAnArray {
    public int[] separateDigits(int[] nums) {
        List<Integer> digits = new ArrayList<>();

        for (int value : nums) {
            List<Integer> current = new ArrayList<>();
            while (value > 0) {
                current.add(value % 10);
                value /= 10;
            }
            Collections.reverse(current);
            digits.addAll(current);
        }

        int[] answer = new int[digits.size()];
        for (int index = 0; index < answer.length; index++) {
            answer[index] = digits.get(index);
        }

        return answer;
    }
}
