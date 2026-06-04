/*
 * LeetCode Problem 125: Valid Palindrome
 * Problem Number: 125
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-palindrome/
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: After removing non-alphanumeric characters, the string is empty.
 * 
 * Constraints:
 * - 1 <= s.length <= 2 * 10^5
 * - s consists only of printable ASCII characters.
 * 
 * Topics: Two Pointers, String
 * Time Complexity: O(n) - single pass through the string
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            
            // Skip non-alphanumeric characters from left
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            }
            // Skip non-alphanumeric characters from right
            else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            }
            // Compare characters (case-insensitive)
            else if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            }
            // Characters match, move both pointers
            else {
                left++;
                right--;
            }
        }
        
        return true;
    }
}
