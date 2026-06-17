/*
LeetCode Problem 152: Maximum Product Subarray
Problem Number: 152
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-product-subarray/

Given an integer array nums, find a contiguous non-empty subarray within the array
that has the largest product, and return the product.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:
- 1 <= nums.length <= 2 * 10^4
- -10 <= nums[i] <= 10

Topics: Array, Dynamic Programming
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - only using constant extra space
*/

package arrays

func maxProduct(nums []int) int {
    maxProduct := nums[0]
    currentMax := nums[0]
    currentMin := nums[0]
    
    for i := 1; i < len(nums); i++ {
        num := nums[i]
        
        // If num is negative, max and min swap
        if num < 0 {
            currentMax, currentMin = currentMin, currentMax
        }
        
        // Update current max and min
        if num > currentMax*num {
            currentMax = num
        } else {
            currentMax = currentMax * num
        }
        
        if num < currentMin*num {
            currentMin = num
        } else {
            currentMin = currentMin * num
        }
        
        // Update global maximum
        if currentMax > maxProduct {
            maxProduct = currentMax
        }
    }
    
    return maxProduct
}
