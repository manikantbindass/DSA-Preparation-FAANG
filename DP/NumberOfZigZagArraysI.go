/*
LeetCode Problem 3699: Number of ZigZag Arrays I
Problem Number: 3699
Difficulty: Hard
Link: https://leetcode.com/problems/number-of-zigzag-arrays-i/

A zigzag array is an array where every adjacent pair of elements alternates
between greater and less than the previous element.

Given n (length of array) and a range [l, r] of possible values,
count the number of zigzag arrays of length n where each element is in [l, r].

Example:
Input: n = 3, l = 1, r = 3
Output: 10

Constraints:
- 1 <= n <= 10^5
- 1 <= l <= r <= 10^5

Topics: Dynamic Programming, Math, Combinatorics
Time Complexity: O(n * m) - where m = r - l + 1
Space Complexity: O(m) - for DP arrays
*/

package dp

const mod = 1000000007

func zigZagArrays(n int, l int, r int) int {
    m := r - l + 1
    
    // dpUp[i] = number of valid sequences ending with value i
    // where the last step was an upward move (current > previous)
    // dpDown[i] = number of valid sequences ending with value i
    // where the last step was a downward move (current < previous)
    dpUp := make([]int64, m)
    dpDown := make([]int64, m)
    for i := 0; i < m; i++ {
        dpUp[i] = 1
        dpDown[i] = 1
    }
    
    // Build sequences of length 2 to n
    for step := 2; step <= n; step++ {
        // Prefix sums for dpDown
        prefixDown := make([]int64, m+1)
        for i := 0; i < m; i++ {
            prefixDown[i+1] = (prefixDown[i] + dpDown[i]) % mod
        }
        
        // Suffix sums for dpUp
        suffixUp := make([]int64, m+1)
        for i := m - 1; i >= 0; i-- {
            suffixUp[i] = (suffixUp[i+1] + dpUp[i]) % mod
        }
        
        newUp := make([]int64, m)
        newDown := make([]int64, m)
        
        for x := 0; x < m; x++ {
            // For downward move: previous value must be greater than current
            newDown[x] = suffixUp[x+1]
            
            // For upward move: previous value must be less than current
            newUp[x] = prefixDown[x]
        }
        
        dpUp = newUp
        dpDown = newDown
    }
    
    // Sum all sequences of length n
    var total int64 = 0
    for i := 0; i < m; i++ {
        total = (total + dpUp[i] + dpDown[i]) % mod
    }
    
    return int(total)
}
