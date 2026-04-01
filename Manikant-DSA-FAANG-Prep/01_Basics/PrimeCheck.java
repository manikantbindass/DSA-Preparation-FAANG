/**
 * Prime Number Check
 * Determine if a given number n is prime.
 *
 * Optimized: check divisors of form 6k+/-1 up to sqrt(n).
 * Time Complexity: O(sqrt(n))
 * Space Complexity: O(1)
 */
public class PrimeCheck {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; (long) i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(2));   // true
        System.out.println(isPrime(17));  // true
        System.out.println(isPrime(18));  // false
        System.out.println(isPrime(97));  // true
    }
}
