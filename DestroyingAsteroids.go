/*
LeetCode Problem 2126: Destroying Asteroids
Problem Number: 2126
Difficulty: Medium
Link: https://leetcode.com/problems/destroying-asteroids/

You are given an integer mass, which represents the starting mass of a planet,
and an integer array asteroids, where asteroids[i] is the mass of the i-th asteroid.

You can destroy asteroids in any order. If the planet's mass is greater than or equal
to the asteroid's mass, the planet absorbs the asteroid and its mass increases by the
asteroid's mass. Otherwise, the planet is destroyed.

Return true if all asteroids can be destroyed, otherwise return false.

Example 1:
Input: mass = 10, asteroids = [3,9,19,5,21]
Output: true
Explanation: One possible way: 10 -> 13 -> 22 -> 41 -> 46 -> 67

Example 2:
Input: mass = 5, asteroids = [4,9,23,4]
Output: false
Explanation: The planet cannot destroy asteroid 9 after destroying 4 and 4.

Constraints:
- 1 <= mass <= 10^5
- 1 <= asteroids.length <= 10^5
- 1 <= asteroids[i] <= 10^5

Topics: Array, Greedy, Sorting
Time Complexity: O(n log n) - due to sorting
Space Complexity: O(1) - excluding the space for sorting
*/

package arrays

import "sort"

func asteroidsDestroyed(mass int, asteroids []int) bool {
    // Sort asteroids in ascending order to destroy smaller ones first
    sort.Ints(asteroids)
    
    currentMass := int64(mass)
    
    for _, asteroid := range asteroids {
        if currentMass < int64(asteroid) {
            return false
        }
        currentMass += int64(asteroid)
    }
    
    return true
}
