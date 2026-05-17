/*
LeetCode Problem 77: Combinations
Problem Number: 77
Difficulty: Medium
Link: https://leetcode.com/problems/combinations/

Given two integers n and k, return all possible combinations of k numbers 
chosen from the range [1, n]. You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: All combinations of choosing 2 numbers from 1 to 4.

Example 2:
Input: n = 1, k = 1
Output: [[1]]

Constraints:
- 1 <= n <= 20
- 1 <= k <= n

Topics: Backtracking
Time Complexity: O(C(n, k) * k) - number of combinations times combination size
Space Complexity: O(k) - for the recursion stack and temporary list
*/

package backtracking

func combine(n int, k int) [][]int {
    result := make([][]int, 0)
    current := make([]int, 0, k)
    
    var dfs func(start int)
    dfs = func(start int) {
        // If current combination size reaches k, add to result
        if len(current) == k {
            temp := make([]int, k)
            copy(temp, current)
            result = append(result, temp)
            return
        }
        
        // If start exceeds n, no more numbers to add
        if start > n {
            return
        }
        
        // Include current number
        current = append(current, start)
        dfs(start + 1)
        
        // Exclude current number (backtrack)
        current = current[:len(current)-1]
        dfs(start + 1)
    }
    
    dfs(1)
    return result
}
