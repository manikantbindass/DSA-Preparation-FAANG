/*
LeetCode Problem 1840: Maximum Building Height
Problem Number: 1840
Difficulty: Hard
Link: https://leetcode.com/problems/maximum-building-height/

You are given an integer n and a 2D integer array restrictions where restrictions[i] = [id_i, maxHeight_i]
indicates that building id_i has a height restriction of maxHeight_i.

There are n buildings numbered 1 to n. The height of each building must be a non-negative integer.
The height difference between adjacent buildings cannot exceed 1.

Return the maximum possible height of the tallest building.

Example 1:
Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The heights could be [0,1,1,1,1] or [1,1,1,1,1]? Actually, with restrictions, max height is 2.

Example 2:
Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5

Example 3:
Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2

Constraints:
- 2 <= n <= 10^9
- 0 <= restrictions.length <= min(n - 1, 10^5)
- 1 <= id_i <= n
- 0 <= maxHeight_i <= 10^9
- id_i is unique.

Topics: Array, Greedy, Sorting
Time Complexity: O(m log m) - where m is the number of restrictions
Space Complexity: O(m) - for storing restrictions list
*/

package arrays

import "sort"

func maxBuilding(n int, restrictions [][]int) int {
    // Copy restrictions and add implicit ones
    r := make([][]int, 0, len(restrictions)+2)
    for _, res := range restrictions {
        r = append(r, []int{res[0], res[1]})
    }
    
    // Add building 1 restriction (must be at least height 0)
    r = append(r, []int{1, 0})
    sort.Slice(r, func(i, j int) bool {
        return r[i][0] < r[j][0]
    })
    
    // Add implicit restriction for the last building (n)
    if r[len(r)-1][0] != n {
        r = append(r, []int{n, n - 1})
    }
    
    m := len(r)
    
    // First pass: left to right - enforce restrictions based on previous building
    for i := 1; i < m; i++ {
        dist := r[i][0] - r[i-1][0]
        if r[i-1][1]+dist < r[i][1] {
            r[i][1] = r[i-1][1] + dist
        }
    }
    
    // Second pass: right to left - enforce restrictions based on next building
    for i := m - 2; i > 0; i-- {
        dist := r[i+1][0] - r[i][0]
        if r[i+1][1]+dist < r[i][1] {
            r[i][1] = r[i+1][1] + dist
        }
    }
    
    // Calculate maximum possible height between each pair of restricted buildings
    maxHeight := 0
    for i := 0; i < m-1; i++ {
        dist := r[i+1][0] - r[i][0]
        // Maximum height achievable between two restrictions
        possible := (r[i][1] + r[i+1][1] + dist) / 2
        if possible > maxHeight {
            maxHeight = possible
        }
    }
    
    return maxHeight
}
