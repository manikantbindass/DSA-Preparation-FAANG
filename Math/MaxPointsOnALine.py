"""
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
"""

from typing import List
from math import gcd
from collections import defaultdict

class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        if n <= 2:
            return n
        
        max_points = 1
        
        for i in range(n):
            slope_count = defaultdict(int)
            duplicate = 0
            current_max = 0
            
            for j in range(i + 1, n):
                dx = points[j][0] - points[i][0]
                dy = points[j][1] - points[i][1]
                
                # Handle duplicate points
                if dx == 0 and dy == 0:
                    duplicate += 1
                    continue
                
                # Reduce slope to simplest form using GCD
                g = gcd(dx, dy)
                dx //= g
                dy //= g
                
                # Use a tuple as the slope key
                slope = (dx, dy)
                slope_count[slope] += 1
                current_max = max(current_max, slope_count[slope])
            
            max_points = max(max_points, current_max + duplicate + 1)
        
        return max_points
