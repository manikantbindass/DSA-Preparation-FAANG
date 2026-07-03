// ──────────────────────────────────────────────────────────────────────
// LeetCode #202 · Happy Number
// Difficulty : Easy
// Topics     : Hash Table, Math, Two Pointers
// URL        : https://leetcode.com/problems/happy-number/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to determine if a number is a happy number. A happy
//   number is defined by repeatedly replacing the number by the sum of the
//   squares of its digits until it becomes 1 (happy) or enters a cycle
//   that does not include 1 (unhappy). The key observation is that the
//   process either reaches 1 or enters a cycle. To detect cycles
//   efficiently, we can use Floyd's cycle detection algorithm (two
//   pointers: slow and fast). The slow pointer moves one step (computes
//   the next number once), and the fast pointer moves two steps (computes
//   the next number twice). If they meet at a number other than 1, a cycle
//   is detected and the number is unhappy. If the fast pointer reaches 1,
//   the number is happy. This approach uses O(1) space and avoids using a
//   hash set. The helper function computes the sum of squares of digits.
// 
// Complexity
//   Time  : O(log n) average, but bounded by the cycle length; effectively O(log n) per step and the number of steps is small.
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

class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }
    
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
