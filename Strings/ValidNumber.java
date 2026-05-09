// LeetCode 65 - Valid Number
// Time Complexity: O(n) | Space Complexity: O(1)
public class ValidNumber {
    public boolean isNumber(String s) {
        int n = s.length();
        int index = 0;

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
        }

        if (index == n) {
            return false;
        }

        if (s.charAt(index) == '.'
                && (index + 1 == n || s.charAt(index + 1) == 'e' || s.charAt(index + 1) == 'E')) {
            return false;
        }

        int dots = 0;
        int exponents = 0;

        for (int current = index; current < n; current++) {
            char ch = s.charAt(current);

            if (ch == '.') {
                if (exponents > 0 || dots > 0) {
                    return false;
                }
                dots++;
            } else if (ch == 'e' || ch == 'E') {
                if (exponents > 0 || current == index || current == n - 1) {
                    return false;
                }
                exponents++;
                if (s.charAt(current + 1) == '+' || s.charAt(current + 1) == '-') {
                    current++;
                    if (current == n - 1) {
                        return false;
                    }
                }
            } else if (ch < '0' || ch > '9') {
                return false;
            }
        }

        return true;
    }
}
