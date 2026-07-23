/*
 * LeetCode Problem 258: Add Digits
 * Problem Number: 258
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/add-digits/
 *
 * Given an integer num, repeatedly add all its digits until the result has only
 * one digit, and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 38
 * Output: 2
 * Explanation: The process is
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2 
 * Since 2 has only one digit, return it.
 *
 * Example 2:
 *
 * Input: num = 0
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 	0 <= num <= 231 - 1
 *
 *
 *
 * Follow up: Could you do it without any loop/recursion in O(1) runtime?
 *
 * Example 1:
 * Input: num = 38
 * Output: 2
 * Explanation: The process is
 *
 * Example 2:
 * Input: num = 0
 * Output: 0
 *
 * Constraints:
 * - 0 <= num <= 231 - 1
 *
 * Topics: Math, Simulation, Number Theory
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.1 MB
 */

class Solution {
    public int addDigits(int num) {
        return (num-1) % 9 + 1;
    }
}
