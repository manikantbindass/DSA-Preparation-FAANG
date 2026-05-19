/*
LeetCode Problem 2540: Minimum Common Value
Problem Number: 2540
Difficulty: Easy
Link: https://leetcode.com/problems/minimum-common-value/

Given two integer arrays nums1 and nums2, sorted in non-decreasing order,
return the minimum integer common to both arrays. If there is no common integer,
return -1.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest common element is 2.

Example 2:
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: The common elements are 2 and 3, and 2 is the smallest.

Constraints:
- 1 <= nums1.length, nums2.length <= 10^5
- 1 <= nums1[i], nums2[i] <= 10^9
- Both arrays are sorted in non-decreasing order.

Topics: Array, Hash Table, Two Pointers, Binary Search
Time Complexity: O(m + n) - single pass through both arrays
Space Complexity: O(1) - only using constant extra space
*/

package arrays

func getCommon(nums1 []int, nums2 []int) int {
    i, j := 0, 0
    m, n := len(nums1), len(nums2)
    
    for i < m && j < n {
        if nums1[i] == nums2[j] {
            return nums1[i]
        } else if nums1[i] < nums2[j] {
            i++
        } else {
            j++
        }
    }
    
    return -1
}
