/*
 * LeetCode Problem 3626: Smallest Divisible Digit Product I
 * Problem Number: 3626
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/smallest-divisible-digit-product-i/
 *
 * You are given two integers n and t. Return the smallest number greater than or
 * equal to n such that the product of its digits is divisible by t.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10, t = 2
 *
 * Output: 10
 *
 * Explanation:
 *
 * The digit product of 10 is 0, which is divisible by 2, making it the smallest
 * number greater than or equal to 10 that satisfies the condition.
 *
 * Example 2:
 *
 * Input: n = 15, t = 3
 *
 * Output: 16
 *
 * Explanation:
 *
 * The digit product of 16 is 6, which is divisible by 3, making it the smallest
 * number greater than or equal to 15 that satisfies the condition.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 100
 * 	1 <= t <= 10
 *
 * Example 1:
 * Input: n = 10, t = 2
 * Output: 10
 *
 * Example 2:
 * Input: n = 15, t = 3
 * Output: 16
 *
 * Constraints:
 * - 1 <= n <= 100
 * - 1 <= t <= 10
 *
 * Topics: Math, Enumeration
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.2 MB
 */

class Solution {
    public int smallestNumber(int n,int t) {
        for (int i=n;; ++i) {
            int p=1;
            for (int x=i;x > 0; x /=10) {
                p *= (x % 10);
            }
            if (p %t==0) {
                return i;
            }
        }
    }
}
