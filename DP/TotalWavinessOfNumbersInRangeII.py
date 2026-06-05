"""
LeetCode Problem 3753: Total Waviness of Numbers in Range II
Problem Number: 3753
Difficulty: Hard
Link: https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/

The waviness of a number is defined as the count of its digits that are either:
- Strictly greater than both adjacent digits (peak), or
- Strictly less than both adjacent digits (valley)

Given two integers num1 and num2, return the total waviness of all numbers
in the inclusive range [num1, num2].

Example:
Input: num1 = 10, num2 = 50
Output: 38

Constraints:
- 1 <= num1 <= num2 <= 10^5 (for Part I)
- For Part II, constraints are larger, requiring digit DP.

Topics: Dynamic Programming, Digit DP, Math, String
Time Complexity: O(log10(num2) * 2 * 2 * 11 * 11) - constant for digit DP
Space Complexity: O(log10(num2) * 2 * 2 * 11 * 11) - memoization size
"""

from functools import lru_cache

class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        def solve(n: int) -> int:
            if n <= 0:
                return 0
            
            digits = list(map(int, str(n)))
            n_digits = len(digits)
            
            @lru_cache(maxsize=None)
            def dfs(pos: int, tight: bool, started: bool, prev2: int, prev1: int) -> tuple:
                """
                Returns (waviness_sum, count) for the current state.
                - pos: current position being processed (0 to n_digits)
                - tight: whether previous digits match the bound
                - started: whether we have started placing non-zero digits
                - prev2: digit two positions before (10 for sentinel)
                - prev1: digit one position before (10 for sentinel)
                """
                if pos == n_digits:
                    return (0, 1)
                
                max_digit = digits[pos] if tight else 9
                total_waviness = 0
                total_count = 0
                
                for d in range(max_digit + 1):
                    new_tight = tight and (d == max_digit)
                    
                    if not started and d == 0:
                        # Skip leading zeros
                        w, c = dfs(pos + 1, new_tight, False, 10, 10)
                    else:
                        new_prev2 = 10 if not started else prev1
                        new_prev1 = d
                        add = 0
                        
                        # Check if this digit creates a waviness with previous two digits
                        if started and prev2 != 10:
                            if (prev1 > prev2 and prev1 > d) or (prev1 < prev2 and prev1 < d):
                                add = 1
                        
                        w, c = dfs(pos + 1, new_tight, True, new_prev2, new_prev1)
                        total_waviness += w + add * c
                        total_count += c
                    
                    total_waviness += w
                    total_count += c
                
                return (total_waviness, total_count)
            
            result, _ = dfs(0, True, False, 10, 10)
            return result
        
        return solve(num2) - solve(num1 - 1)
