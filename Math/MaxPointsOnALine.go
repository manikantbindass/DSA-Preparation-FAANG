/*
LeetCode Problem 149: Max Points on a Line
Problem Number: 149
Difficulty: Hard
Link: https://leetcode.com/problems/max-points-on-a-line/

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4

Constraints:
- 1 <= points.length <= 300
- points[i].length == 2
- -10^4 <= xi, yi <= 10^4
- All points are unique.

Topics: Array, Hash Table, Math, Geometry
Time Complexity: O(n²) - where n is the number of points
Space Complexity: O(n) - for the slope map
*/

package math

func maxPoints(points [][]int) int {
    n := len(points)
    if n <= 2 {
        return n
    }
    
    maxPoints := 1
    
    for i := 0; i < n; i++ {
        slopeCount := make(map[[2]int]int)
        duplicate := 0
        currentMax := 0
        
        for j := i + 1; j < n; j++ {
            dx := points[j][0] - points[i][0]
            dy := points[j][1] - points[i][1]
            
            // Handle duplicate points
            if dx == 0 && dy == 0 {
                duplicate++
                continue
            }
            
            // Reduce slope to simplest form using GCD
            g := gcd(dx, dy)
            dx /= g
            dy /= g
            
            // Use an array as the slope key
            slope := [2]int{dx, dy}
            slopeCount[slope]++
            if slopeCount[slope] > currentMax {
                currentMax = slopeCount[slope]
            }
        }
        
        if currentMax+duplicate+1 > maxPoints {
            maxPoints = currentMax + duplicate + 1
        }
    }
    
    return maxPoints
}

func gcd(a, b int) int {
    for b != 0 {
        a, b = b, a%b
    }
    if a < 0 {
        return -a
    }
    return a
}
