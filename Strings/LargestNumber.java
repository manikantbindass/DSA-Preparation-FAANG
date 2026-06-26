/*
 * LeetCode Problem 179: Largest Number
 * Problem Number: 179
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/largest-number/
 * 
 * Given a list of non-negative integers nums, arrange them such that they form the largest number.
 * 
 * Example 1:
 * Input: nums = [10,2]
 * Output: "210"
 * 
 * Example 2:
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * 
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 10^9
 * 
 * Topics: String, Sorting, Greedy
 * Time Complexity: O(n log n) - for sorting
 * Space Complexity: O(n) - for the string array
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String largestNumber(int[] nums) {
        // Convert numbers to strings
        List<String> numStrs = new ArrayList<>();
        for (int num : nums) {
            numStrs.add(String.valueOf(num));
        }
        
        // Sort using custom comparator: compare concatenated results
        numStrs.sort((a, b) -> (b + a).compareTo(a + b));
        
        // If the largest number is "0", the result should be "0"
        if (numStrs.get(0).equals("0")) {
            return "0";
        }
        
        // Build the result
        StringBuilder result = new StringBuilder();
        for (String s : numStrs) {
            result.append(s);
        }
        
        return result.toString();
    }
}
