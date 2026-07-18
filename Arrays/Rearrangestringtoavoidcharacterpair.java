/*
 * LeetCode Problem 0: rearrange-string-to-avoid-character-pair
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rearrange-string-to-avoid-character-pair/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution{
    public String rearrangeString(String s, char x, char y) {
        StringBuilder a= new StringBuilder();
        StringBuilder b= new StringBuilder();
        StringBuilder c= new StringBuilder();
        for (char ch :s.toCharArray()) {
            if (ch== y) {
                a.append(ch);
            } else if (ch== x) {
                c.append(ch);
            } else {
                b.append(ch);
            }
        }
        return a.append(b).append(c).toString();
    }
}
