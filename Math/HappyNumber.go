// ──────────────────────────────────────────────────────────────────────
// LeetCode #202 · Happy Number
// Difficulty : Easy
// Topics     : Hash Table, Math, Two Pointers
// URL        : https://leetcode.com/problems/happy-number/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to determine if a number is a happy number. A happy
//   number eventually reaches 1 when repeatedly replaced by the sum of the
//   squares of its digits; otherwise, it enters a cycle that does not
//   include 1. The solution uses Floyd's cycle detection algorithm (two
//   pointers: slow and fast) to detect cycles without extra space. The
//   slow pointer moves one step (computes sum of squares once), and the
//   fast pointer moves two steps (computes sum of squares twice). If they
//   meet at 1, the number is happy; if they meet at any other number, a
//   cycle exists and the number is not happy. This approach is efficient
//   with O(log n) time per step and O(1) space.
// 
// Complexity
//   Time  : O(log n) per step, overall O(log n) due to cycle detection
//   Space : O(1)
// 
// Runtime  : 0 ms
// Memory   : 42 MB
// 
// Examples
//   Example 1:
//     Input  : n = 19
//     Output : true
//   Example 2:
//     Input  : n = 2
//     Output : false
// 
// Constraints
//   · 1 <= n <= 231 - 1
// ──────────────────────────────────────────────────────────────────────

func isHappy(n int) bool {
    getNext := func(num int) int {
        sum := 0
        for num > 0 {
            digit := num % 10
            sum += digit * digit
            num /= 10
        }
        return sum
    }
    
    slow := n
    fast := getNext(n)
    for fast != 1 && slow != fast {
        slow = getNext(slow)
        fast = getNext(getNext(fast))
    }
    return fast == 1
}
