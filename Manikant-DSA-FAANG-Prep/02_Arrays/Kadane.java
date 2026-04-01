/**
 * Maximum Subarray - Kadane's Algorithm
 * LeetCode #53
 * Find the contiguous subarray with the largest sum.
 *
 * Approach: At each index, decide to extend existing subarray or start fresh.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class Kadane {
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(maxSubArray(new int[]{1}));                               // 1
        System.out.println(maxSubArray(new int[]{5, 4, -1, 7, 8}));                 // 23
    }
}
