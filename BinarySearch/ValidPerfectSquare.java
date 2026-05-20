// LeetCode 367 - Valid Perfect Square
// Time Complexity: O(log n) | Space Complexity: O(1)
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == num) {
                return true;
            }
            if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
