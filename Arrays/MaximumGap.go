/*
LeetCode Problem 164: Maximum Gap
Problem Number: 164
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-gap/

Given an integer array nums, return the maximum difference between two successive elements
in its sorted form. If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form is [1,3,6,9], and the maximum gap is 3 (between 3 and 6, or 6 and 9).

Example 2:
Input: nums = [10]
Output: 0

Constraints:
- 1 <= nums.length <= 10^5
- 0 <= nums[i] <= 10^9

Topics: Array, Sorting, Bucket Sort
Time Complexity: O(n) - using bucket sort
Space Complexity: O(n) - for the buckets
*/

package arrays

import "math"

func maximumGap(nums []int) int {
    n := len(nums)
    if n < 2 {
        return 0
    }
    
    // Find the minimum and maximum values
    minVal := math.MaxInt32
    maxVal := math.MinInt32
    for _, num := range nums {
        if num < minVal {
            minVal = num
        }
        if num > maxVal {
            maxVal = num
        }
    }
    
    // If all elements are the same, gap is 0
    if minVal == maxVal {
        return 0
    }
    
    // Calculate bucket size and number of buckets
    bucketSize := max(1, (maxVal-minVal)/(n-1))
    bucketCount := (maxVal-minVal)/bucketSize + 1
    
    // Initialize buckets with min and max values
    bucketMin := make([]int, bucketCount)
    bucketMax := make([]int, bucketCount)
    for i := 0; i < bucketCount; i++ {
        bucketMin[i] = math.MaxInt32
        bucketMax[i] = math.MinInt32
    }
    
    // Place each number into its bucket
    for _, num := range nums {
        index := (num - minVal) / bucketSize
        if num < bucketMin[index] {
            bucketMin[index] = num
        }
        if num > bucketMax[index] {
            bucketMax[index] = num
        }
    }
    
    // Calculate maximum gap
    maxGap := 0
    prevMax := minVal
    for i := 0; i < bucketCount; i++ {
        // Skip empty buckets
        if bucketMin[i] == math.MaxInt32 {
            continue
        }
        // Gap between previous bucket's max and current bucket's min
        gap := bucketMin[i] - prevMax
        if gap > maxGap {
            maxGap = gap
        }
        prevMax = bucketMax[i]
    }
    
    return maxGap
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
