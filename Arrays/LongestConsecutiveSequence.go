/*
LeetCode Problem 128: Longest Consecutive Sequence
Problem Number: 128
Difficulty: Medium
Link: https://leetcode.com/problems/longest-consecutive-sequence/

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Constraints:
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9

Topics: Array, Hash Table, Union Find
Time Complexity: O(n) - each element is processed at most twice
Space Complexity: O(n) - for the hash set
*/

package arrays

func longestConsecutive(nums []int) int {
    if len(nums) == 0 {
        return 0
    }
    
    numSet := make(map[int]bool)
    for _, num := range nums {
        numSet[num] = true
    }
    
    longestStreak := 0
    
    for _, num := range nums {
        // Only start counting if current number is the start of a sequence
        if !numSet[num-1] {
            currentNum := num
            currentStreak := 1
            
            for numSet[currentNum+1] {
                currentNum++
                currentStreak++
            }
            
            if currentStreak > longestStreak {
                longestStreak = currentStreak
            }
        }
    }
    
    return longestStreak
}
