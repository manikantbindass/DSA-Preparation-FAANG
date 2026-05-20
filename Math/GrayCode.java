/*
 * LeetCode Problem 89: Gray Code
 * Problem Number: 89
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/gray-code/
 * 
 * An n-bit gray code sequence is a sequence of 2^n integers where:
 * - Every integer is in the inclusive range [0, 2^n - 1].
 * - The first integer is 0.
 * - An integer appears no more than once in the sequence.
 * - The binary representation of every pair of adjacent integers differs by exactly one bit.
 * - The binary representation of the first and last integers also differs by exactly one bit.
 * 
 * Given an integer n, return any valid n-bit gray code sequence.
 * 
 * Example 1:
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * Example 2:
 * Input: n = 1
 * Output: [0,1]
 * 
 * Constraints:
 * - 1 <= n <= 16
 * 
 * Topics: Math, Backtracking, Bit Manipulation
 * Time Complexity: O(2^n) - generating all 2^n numbers
 * Space Complexity: O(1) - excluding the output list
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        // Generate Gray code using formula: i ^ (i >> 1)
        for (int i = 0; i < (1 << n); ++i) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
