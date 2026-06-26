/*
 * LeetCode Problem 3739: Count Subarrays With Majority Element II
 * Problem Number: 3739
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-subarrays-with-majority-element-ii/
 * 
 * Given an array nums and an integer target, return the number of subarrays where the
 * majority element (appearing more than half the subarray length) is the target.
 * 
 * Example:
 * Input: nums = [1,2,1,1], target = 1
 * Output: 3
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 0 <= nums[i] <= 10^9
 * - 0 <= target <= 10^9
 * 
 * Topics: Array, Binary Indexed Tree, Prefix Sum
 * Time Complexity: O(n log n) - for Fenwick Tree operations
 * Space Complexity: O(n) - for the BIT array
 */

class BinaryIndexedTree {
    private int n;
    private int[] tree;
    
    public BinaryIndexedTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }
    
    public void update(int index, int delta) {
        while (index <= n) {
            tree[index] += delta;
            index += index & -index;
        }
    }
    
    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }
}

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        BinaryIndexedTree bit = new BinaryIndexedTree(2 * n + 1);
        
        // Offset to handle negative indices
        int prefix = n + 1;
        bit.update(prefix, 1);
        long result = 0;
        
        for (int num : nums) {
            // Increment prefix if num is target, otherwise decrement
            prefix += (num == target) ? 1 : -1;
            // Count previous prefixes less than current prefix
            result += bit.query(prefix - 1);
            // Add current prefix to BIT
            bit.update(prefix, 1);
        }
        
        return result;
    }
}
