/*
 * LeetCode Problem 3043: Find the Length of the Longest Common Prefix
 * Problem Number: 3043
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
 * 
 * You are given two arrays with positive integers arr1 and arr2.
 * A prefix of a positive integer is an integer formed by one or more of its digits,
 * starting from its leftmost digit. For example, 123 is a prefix of the integer 12345,
 * while 234 is not.
 * 
 * A common prefix of two integers a and b is a prefix that is a prefix of both a and b.
 * 
 * Return the length of the longest common prefix among all pairs of integers
 * (a, b) such that a is from arr1 and b is from arr2. If no common prefix exists, return 0.
 * 
 * Example 1:
 * Input: arr1 = [1,10,100], arr2 = [1000]
 * Output: 3
 * Explanation: The longest common prefix between 100 and 1000 is "100" (length 3).
 * 
 * Example 2:
 * Input: arr1 = [1,2,3], arr2 = [4,4,4]
 * Output: 0
 * Explanation: There is no common prefix between any pair.
 * 
 * Constraints:
 * - 1 <= arr1.length, arr2.length <= 5 * 10^4
 * - 1 <= arr1[i], arr2[i] <= 10^8
 * 
 * Topics: Array, Hash Table, String, Trie
 * Time Complexity: O(N * L + M * L) where L is the average length of numbers
 * Space Complexity: O(N * L) - for storing prefixes of arr1
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();
        
        // Store all prefixes of numbers in arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }
        
        int maxLen = 0;
        
        // Check prefixes of numbers in arr2 against the set
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    maxLen = Math.max(maxLen, String.valueOf(num).length());
                    break;
                }
                num /= 10;
            }
        }
        
        return maxLen;
    }
}
