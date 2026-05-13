/*
 * LeetCode Problem 1674: Minimum Moves to Make Array Complementary
 * Problem Number: 1674
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-moves-to-make-array-complementary/
 * 
 * You are given an integer array nums of even length n and an integer limit.
 * In one move, you can replace any element with any integer between 1 and limit inclusive.
 * The array is complementary if for all i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number.
 * 
 * Return the minimum number of moves required to make the array complementary.
 * 
 * Example 1:
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: Replace 1 with 2, then sums become 2+3=5, 4+1=5.
 * 
 * Example 2:
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * 
 * Example 3:
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * 
 * Constraints:
 * - n == nums.length
 * - 2 <= n <= 10^5
 * - n is even
 * - 1 <= nums[i] <= limit <= 10^5
 * 
 * Topics: Array, Greedy, Prefix Sum, Difference Array
 * Time Complexity: O(n + limit) - single pass through pairs and difference array
 * Space Complexity: O(limit) - difference array of size 2*limit+2
 */

class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;
        
        for (int i = 0; i < n / 2; ++i) {
            int a = nums[i];
            int b = nums[n - i - 1];
            int low = Math.min(a, b);
            int high = Math.max(a, b);
            int sum = a + b;
                                                                              
                                                                              // Range [2, 2*limit] represents possible target sums
                                                                              // Case 1: 0 moves needed - target sum = a + b
                                                                              // Case 2: 1 move needed - change one number
                                                                              // Case 3: 2 moves needed - change both numbers
                                                                              
                                                                              // All targets from 2 to 2*limit start with 2 moves
            diff[2] += 2;
                                                                                    
                                                                                    // For targets in [2, low], need 2 moves (no change can achieve)
                                                                                    // For targets in [low+1, high+limit], need 1 move
                                                                                    // For target = sum, need 0 moves
                                                                                    // For targets in [high+limit+1, 2*limit], need 2 moves
                                                                                    
                                                                                    // Subtract 2 from the range where 2 moves are not needed
            diff[low + 1] -= 1;
            diff[sum] -= 1;
            diff[sum + 1] += 1;
            diff[high + limit + 1] += 1;
        }
        
        int ans = n;
        int current = 0;
        for (int i = 2; i < diff.length; ++i) {
            current += diff[i];
            ans = Math.min(ans, current);
        }
        
        return ans;
    }
}
