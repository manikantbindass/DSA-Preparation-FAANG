// ──────────────────────────────────────────────────────────────────────
// LeetCode #201 · Bitwise AND of Numbers Range
// Difficulty : Medium
// Topics     : Bit Manipulation
// URL        : https://leetcode.com/problems/bitwise-and-of-numbers-range/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The bitwise AND of a range [left, right] is determined by the common
//   prefix of the binary representations of left and right. The algorithm
//   repeatedly clears the lowest set bit of right until right <= left.
//   This works because any bit that changes within the range will be zero
//   in the AND result. The final value of right is the common prefix (the
//   AND of all numbers).
// 
// Complexity
//   Time  : O(log n)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.2 MB
// 
// Examples
//   Example 1:
//     Input  : left = 5, right = 7
//     Output : 4
//   Example 2:
//     Input  : left = 0, right = 0
//     Output : 0
//   Example 3:
//     Input  : left = 1, right = 2147483647
//     Output : 0
// 
// Constraints
//   · 0 <= left <= right <= 231 - 1
// ──────────────────────────────────────────────────────────────────────

func rangeBitwiseAnd(left int, right int) int {
    for left < right {
        right &= (right - 1)
    }
    return right
}
