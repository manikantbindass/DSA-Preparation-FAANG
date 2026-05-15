/*
LeetCode Problem 69: Sqrt(x)
Problem Number: 69
Difficulty: Easy
Link: https://leetcode.com/problems/sqrtx/

Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

Example 1:
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.828..., and since we round down, we return 2.

Constraints:
- 0 <= x <= 2^31 - 1

Topics: Math, Binary Search
Time Complexity: O(log x) - binary search on range [0, x]
Space Complexity: O(1) - only using constant extra space
*/

package binarysearch

func mySqrt(x int) int {
    left, right := 0, x
    
    for left < right {
        // Use upper mid to avoid infinite loop
        mid := (left + right + 1) >> 1
        
        // Check if mid * mid > x without overflow
        if mid > x/mid {
            right = mid - 1
        } else {
            left = mid
        }
    }
    
    return left
}
