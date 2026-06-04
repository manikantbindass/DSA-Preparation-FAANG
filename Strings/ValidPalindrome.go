/*
LeetCode Problem 125: Valid Palindrome
Problem Number: 125
Difficulty: Easy
Link: https://leetcode.com/problems/valid-palindrome/

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
and removing all non-alphanumeric characters, it reads the same forward and backward.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: After removing non-alphanumeric characters, the string is empty.

Constraints:
- 1 <= s.length <= 2 * 10^5
- s consists only of printable ASCII characters.

Topics: Two Pointers, String
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - only using constant extra space
*/

package strings

import "unicode"

func isPalindrome(s string) bool {
    left, right := 0, len(s)-1
    
    for left < right {
        leftChar := rune(s[left])
        rightChar := rune(s[right])
        
        // Skip non-alphanumeric characters from left
        if !unicode.IsLetter(leftChar) && !unicode.IsDigit(leftChar) {
            left++
        } else if !unicode.IsLetter(rightChar) && !unicode.IsDigit(rightChar) {
            // Skip non-alphanumeric characters from right
            right--
        } else if unicode.ToLower(leftChar) != unicode.ToLower(rightChar) {
            // Compare characters (case-insensitive)
            return false
        } else {
            // Characters match, move both pointers
            left++
            right--
        }
    }
    
    return true
}
