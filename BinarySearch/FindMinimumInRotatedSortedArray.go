/*
LeetCode Problem 153: Find Minimum in Rotated Sorted Array
Problem Number: 153
Difficulty: Medium
Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:
- [4,5,6,7,0,1,2] if rotated 4 times.
- [0,1,2,4,5,6,7] if rotated 0 times.

Given the sorted rotated array nums of unique elements, return the minimum element.
You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,3,4,5,6,7] rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] rotated 0 times.

Constraints:
- n == nums.length
- 1 <= n <= 5000
- -5000 <= nums[i] <= 5000
- All integers in nums are unique.
- nums is sorted and rotated between 1 and n times.

Topics: Array, Binary Search
Time Complexity: O(log n) - binary search on rotated array
Space Complexity: O(1) - only using constant extra space
*/

package binarysearch

func findMin(nums []int) int {
    left, right := 0, len(nums)-1
    
    for left < right {
        mid := (left + right) >> 1
        
        // If mid element is greater than last element, minimum is in right half
        if nums[mid] > nums[len(nums)-1] {
            left = mid + 1
        } else {
            // Otherwise, minimum is in left half (including mid)
            right = mid
        }
    }
    
    return nums[left]
}
