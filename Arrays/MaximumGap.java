/*
 * LeetCode Problem 164: Maximum Gap
 * Problem Number: 164
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-gap/
 * 
 * Given an integer array nums, return the maximum difference between two successive elements
 * in its sorted form. If the array contains less than two elements, return 0.
 * 
 * You must write an algorithm that runs in linear time and uses linear extra space.
 * 
 * Example 1:
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form is [1,3,6,9], and the maximum gap is 3 (between 3 and 6, or 6 and 9).
 * 
 * Example 2:
 * Input: nums = [10]
 * Output: 0
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 0 <= nums[i] <= 10^9
 * 
 * Topics: Array, Sorting, Bucket Sort
 * Time Complexity: O(n) - using bucket sort
 * Space Complexity: O(n) - for the buckets
 */

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        
        // Find the minimum and maximum values
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        
        // If all elements are the same, gap is 0
        if (minVal == maxVal) {
            return 0;
        }
        
        // Calculate bucket size and number of buckets
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketCount = (maxVal - minVal) / bucketSize + 1;
        
        // Initialize buckets with min and max values
        int[][] buckets = new int[bucketCount][2];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i][0] = Integer.MAX_VALUE;  // min in bucket
            buckets[i][1] = Integer.MIN_VALUE;  // max in bucket
        }
        
        // Place each number into its bucket
        for (int num : nums) {
            int index = (num - minVal) / bucketSize;
            buckets[index][0] = Math.min(buckets[index][0], num);
            buckets[index][1] = Math.max(buckets[index][1], num);
        }
        
        // Calculate maximum gap
        int maxGap = 0;
        int prevMax = minVal;
        for (int i = 0; i < bucketCount; i++) {
            // Skip empty buckets
            if (buckets[i][0] == Integer.MAX_VALUE) {
                continue;
            }
            // Gap between previous bucket's max and current bucket's min
            maxGap = Math.max(maxGap, buckets[i][0] - prevMax);
            prevMax = buckets[i][1];
        }
        
        return maxGap;
    }
}
