/*
 * LeetCode Problem 239: Sliding Window Maximum
 * Problem Number: 239
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/sliding-window-maximum/
 *
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can only
 * see the k numbers in the window. Each time the sliding window moves right by one
 * position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation: 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	-104 <= nums[i] <= 104
 * 	1 <= k <= nums.length
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -104 <= nums[i] <= 104
 * - 1 <= k <= nums.length
 *
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Monotonic Queue
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> q =new PriorityQueue<>((a, b)-> a[0] ==b[0] ? a[1] -b[1]:b[0]- a[0]);
        int n= nums.length;
        for (int i = 0; i < k -1; ++i) {
            q.offer(new int[] {nums[i], i});
        }
        int[] ans =new int[n - k + 1];
        for (int i = k- 1,j = 0; i < n; ++i) {
            q.offer(new int[] {nums[i], i});
            while (q.peek()[1]<= i - k) {
                q.poll();
            }
            ans[j++]= q.peek()[0];
        }
        return ans;
    }
}
