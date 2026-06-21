/*
LeetCode Problem 169: Majority Element
Problem Number: 169
Difficulty: Easy
Link: https://leetcode.com/problems/majority-element/

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
- n == nums.length
- 1 <= n <= 5 * 10^4
- -10^9 <= nums[i] <= 10^9

Topics: Array, Hash Table, Divide and Conquer, Sorting, Counting
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - only using constant extra space
*/

package arrays

func majorityElement(nums []int) int {
    candidate := 0
    count := 0
    
    // Boyer-Moore Majority Vote Algorithm
    for _, num := range nums {
        if count == 0 {
            candidate = num
            count = 1
        } else if candidate == num {
            count++
        } else {
            count--
        }
    }
    
    return candidate
}
