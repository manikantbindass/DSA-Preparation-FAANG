/*
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
*/

package dp

import "strconv"

type MemoKey struct {
    pos     int
    tight   int
    started int
    prev2   int
    prev1   int
}

func totalWaviness(num1 int64, num2 int64) int64 {
    return solve(num2) - solve(num1-1)
}

func solve(n int64) int64 {
    if n <= 0 {
        return 0
    }
    
    digits := []int{}
    for _, ch := range strconv.FormatInt(n, 10) {
        digits = append(digits, int(ch-'0'))
    }
    
    memo := make(map[MemoKey][2]int64)
    
    var dfs func(pos int, tight int, started int, prev2 int, prev1 int) (int64, int64)
    dfs = func(pos int, tight int, started int, prev2 int, prev1 int) (int64, int64) {
        if pos == len(digits) {
            return 0, 1
        }
        
        key := MemoKey{pos, tight, started, prev2, prev1}
        if val, exists := memo[key]; exists {
            return val[0], val[1]
        }
        
        maxDigit := 9
        if tight == 1 {
            maxDigit = digits[pos]
        }
        
        var wavinessSum int64 = 0
        var count int64 = 0
        
        for d := 0; d <= maxDigit; d++ {
            newTight := 0
            if tight == 1 && d == maxDigit {
                newTight = 1
            }
            
            if started == 0 && d == 0 {
                w, c := dfs(pos+1, newTight, 0, 10, 10)
                wavinessSum += w
                count += c
            } else {
                newPrev2 := 10
                if started == 1 {
                    newPrev2 = prev1
                }
                newPrev1 := d
                add := int64(0)
                
                if started == 1 && prev2 != 10 {
                    if (prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d) {
                        add = 1
                    }
                }
                
                w, c := dfs(pos+1, newTight, 1, newPrev2, newPrev1)
                wavinessSum += w + add*c
                count += c
            }
        }
        
        memo[key] = [2]int64{wavinessSum, count}
        return wavinessSum, count
    }
    
    result, _ := dfs(0, 1, 0, 10, 10)
    return result
}
