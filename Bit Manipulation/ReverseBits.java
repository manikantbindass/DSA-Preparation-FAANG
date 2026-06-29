// ──────────────────────────────────────────────────────────────────────
// LeetCode #190 · Reverse Bits
// Difficulty : Easy
// Topics     : Divide and Conquer, Bit Manipulation
// URL        : https://leetcode.com/problems/reverse-bits/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The algorithm reverses the bits of a 32-bit integer by iterating
//   through each bit. For each bit, we extract the least significant bit
//   (LSB) of the input using `n & 1`, then place it at the corresponding
//   reversed position by left-shifting it by `(31 - i)`. The result is
//   accumulated using bitwise OR. The input is then right-shifted
//   (unsigned) to process the next bit. The loop runs at most 32 times,
//   but early termination occurs when `n` becomes 0, which optimizes for
//   numbers with fewer significant bits. This approach is efficient and
//   works for all 32-bit integers.
// 
// Complexity
//   Time  : O(1) — fixed 32 iterations worst-case, but early exit reduces average time
//   Space : O(1) — only a few integer variables
// 
// Runtime  : 0 ms
// Memory   : 42.4 MB
// 
// Examples
//   Example 1:
//     Input  : n = 43261596
//     Output : 964176192
//   Example 2:
//     Input  : n = 2147483644
//     Output : 1073741822
// 
// Constraints
//   · 0 <= n <= 231 - 2
//   · n is even.
// ──────────────────────────────────────────────────────────────────────

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            ans |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return ans;
    }
}
