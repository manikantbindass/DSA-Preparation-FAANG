/**
 * Palindrome Number
 * LeetCode #9
 * Determine whether an integer is a palindrome without converting to a string.
 *
 * Time Complexity: O(log10(n))
 * Space Complexity: O(1)
 */
public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));   // true
        System.out.println(isPalindrome(-121));  // false
        System.out.println(isPalindrome(10));    // false
        System.out.println(isPalindrome(1221));  // true
    }
}
