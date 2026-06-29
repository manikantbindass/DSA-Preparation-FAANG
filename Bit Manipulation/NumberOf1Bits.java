// ──────────────────────────────────────────────────────────────────────
// LeetCode #191 · Number of 1 Bits
// Difficulty : Easy
// Topics     : Divide and Conquer, Bit Manipulation
// URL        : https://leetcode.com/problems/number-of-1-bits/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to count the number of 1 bits (set bits) in the binary
//   representation of a positive integer. The accepted solution uses a
//   loop that iterates over each bit of the 32-bit integer. For each
//   iteration, it checks the least significant bit using n & 1, and if it
//   is 1, it sets the corresponding bit in the result at the mirrored
//   position (31 - i). Then it right-shifts n by 1 (using unsigned shift
//   >>> to handle negative numbers in Java, though the problem constraints
//   ensure n is positive). The loop continues until n becomes 0 or all 32
//   bits are processed. This approach is efficient and works in O(1) time
//   since the number of iterations is at most 32. For the follow-up
//   optimization when called many times, we can use a lookup table for
//   8-bit or 16-bit chunks to reduce the number of operations, or use
//   built-in functions like Integer.bitCount() in Java. The solutions
//   provided implement the same algorithm in Java, Python, and Go.
// 
// Complexity
//   Time  : O(1) — the loop runs at most 32 times for a 32-bit integer
//   Space : O(1) — only a few integer variables are used
// 
// Runtime  : 0 ms
// Memory   : 42 MB
// 
// Examples
//   Example 1:
//     Input  : n = 11
//     Output : 3
//   Example 2:
//     Input  : n = 128
//     Output : 1
//   Example 3:
//     Input  : n = 2147483645
//     Output : 30
// 
// Constraints
//   · 1 <= n <= 231 - 1
// ──────────────────────────────────────────────────────────────────────

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }
}
