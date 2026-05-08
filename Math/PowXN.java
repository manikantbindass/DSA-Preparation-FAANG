// LeetCode 50 - Pow(x, n)
// Time Complexity: O(log n) | Space Complexity: O(1)
public class PowXN {
    public double myPow(double x, int n) {
        return n >= 0 ? fastPow(x, n) : 1.0 / fastPow(x, -(long) n);
    }

    private double fastPow(double base, long exponent) {
        double answer = 1.0;

        while (exponent > 0) {
            if ((exponent & 1L) == 1L) {
                answer *= base;
            }
            base *= base;
            exponent >>= 1;
        }

        return answer;
    }
}
