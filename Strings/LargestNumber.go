/*
LeetCode Problem 179: Largest Number
Problem Number: 179
Difficulty: Medium
Link: https://leetcode.com/problems/largest-number/

Given a list of non-negative integers nums, arrange them such that they form the largest number.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:
- 1 <= nums.length <= 100
- 0 <= nums[i] <= 10^9

Topics: String, Sorting, Greedy
Time Complexity: O(n log n) - for sorting
Space Complexity: O(n) - for the string array
*/

package strings

import (
    "sort"
    "strconv"
    "strings"
)

func largestNumber(nums []int) string {
    // Convert numbers to strings
    numStrs := make([]string, len(nums))
    for i, num := range nums {
        numStrs[i] = strconv.Itoa(num)
    }
    
    // Sort using custom comparator: compare concatenated results
    sort.Slice(numStrs, func(i, j int) bool {
        return numStrs[i]+numStrs[j] > numStrs[j]+numStrs[i]
    })
    
    // If the largest number is "0", the result should be "0"
    if numStrs[0] == "0" {
        return "0"
    }
    
    // Build the result
    return strings.Join(numStrs, "")
}
