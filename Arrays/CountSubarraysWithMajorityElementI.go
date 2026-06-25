/*
LeetCode Problem 3737: Count Subarrays With Majority Element I
Problem Number: 3737
Difficulty: Medium
Link: https://leetcode.com/problems/count-subarrays-with-majority-element-i/

Given an array of integers nums and an integer target, count the number of subarrays
where target appears more than half of the subarray length.

Example:
Input: nums = [1,2,1,3], target = 1
Output: 3
Explanation: The subarrays where 1 appears more than half are:
[1] (length 1, count 1), [1,2,1] (length 3, count 2), [1] (at index 2)

Constraints:
- 1 <= nums.length <= 10^5
- 1 <= nums[i] <= 10^9

Topics: Array, Hash Table, Sliding Window
Time Complexity: O(n^2) - brute force approach
Space Complexity: O(n) - for frequency map
*/

package arrays

func countMajoritySubarrays(nums []int, target int) int {
    n := len(nums)
    ans := 0
    
    // Check every subarray
    for i := 0; i < n; i++ {
        freq := make(map[int]int)
        for j := i; j < n; j++ {
            length := j - i + 1
            freq[nums[j]]++
            if freq[target] > length/2 {
                ans++
            }
        }
    }
    
    return ans
}
