// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · subsequence-after-one-replacement
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/subsequence-after-one-replacement/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The algorithm iterates through each number in the array. For each
//   number, it extracts its digits to find the minimum digit (lo) and
//   maximum digit (hi). The range for that number is hi - lo. It keeps
//   track of the maximum range seen so far (mx) and the corresponding
//   answer. If a number has a larger range, it updates mx and sets ans to
//   that number. If the range equals the current maximum, it adds the
//   number to ans. Finally, it returns ans.
// 
// Complexity
//   Time  : O(n * d) where n is the number of integers and d is the average number of digits (at most 10 for 32-bit integers)
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42.5 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func maxDigitRange(nums []int) int {
    mx := -1
    ans := 0
    for _, x := range nums {
        t := x
        lo, hi := 9, 0
        for t > 0 {
            d := t % 10
            if d < lo {
                lo = d
            }
            if d > hi {
                hi = d
            }
            t /= 10
        }
        r := hi - lo
        if r > mx {
            mx = r
            ans = x
        } else if r == mx {
            ans += x
        }
    }
    return ans
}
