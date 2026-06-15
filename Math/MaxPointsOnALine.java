/*
 * LeetCode Problem 149: Max Points on a Line
 * Problem Number: 149
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/max-points-on-a-line/
 * 
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
 * return the maximum number of points that lie on the same straight line.
 * 
 * Example 1:
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * 
 * Example 2:
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * 
 * Constraints:
 * - 1 <= points.length <= 300
 * - points[i].length == 2
 * - -10^4 <= xi, yi <= 10^4
 * - All points are unique.
 * 
 * Topics: Array, Hash Table, Math, Geometry
 * Time Complexity: O(n²) - where n is the number of points
 * Space Complexity: O(n) - for the slope map
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        
        int maxPoints = 1;
        
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 0;
            int currentMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Handle duplicate points
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                
                // Reduce slope to simplest form using GCD
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                // Use a string key to represent the slope
                String slope = dx + "/" + dy;
                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, slopeCount.get(slope));
            }
            
            maxPoints = Math.max(maxPoints, currentMax + duplicate + 1);
        }
        
        return maxPoints;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
