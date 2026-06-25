/*
 * LeetCode Problem 3737: Count Subarrays With Majority Element I
 * Problem Number: 3737
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-subarrays-with-majority-element-i/
 * 
 * Given an array of integers nums and an integer target, count the number of subarrays
 * where target appears more than half of the subarray length.
 * 
 * Example:
 * Input: nums = [1,2,1,3], target = 1
 * Output: 3
 * Explanation: The subarrays where 1 appears more than half are:
 * [1] (length 1, count 1), [1,2,1] (length 3, count 2), [1] (at index 2)
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * 
 * Topics: Array, Hash Table, Sliding Window
 * Time Complexity: O(n^2) - brute force approach
 * Space Complexity: O(n) - for frequency map
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Check every subarray
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int length = j - i + 1;
                // Add current element to frequency map
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
                // Check if target appears more than half
                if (freq.getOrDefault(target, 0) > length / 2) {
                    ans++;
                }
            }
            // Clear frequency map for next starting position
            freq.clear();
        }
        
        return ans;
    }
}
