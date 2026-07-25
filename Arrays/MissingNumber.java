/*
 * LeetCode Problem 268: Missing Number
 * Problem Number: 268
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/missing-number/
 *
 * Given an array nums containing n distinct numbers in the range [0, n], return
 * the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 *
 * Output: 2
 *
 * Explanation:
 *
 * n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the
 * missing number in the range since it does not appear in nums.
 *
 * Example 2:
 *
 * Input: nums = [0,1]
 *
 * Output: 2
 *
 * Explanation:
 *
 * n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the
 * missing number in the range since it does not appear in nums.
 *
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 *
 * Output: 8
 *
 * Explanation:
 *
 * n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the
 * missing number in the range since it does not appear in nums.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 	n == nums.length
 * 	1 <= n <= 104
 * 	0 <= nums[i] <= n
 * 	All the numbers of nums are unique.
 *
 *
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity
 * and O(n) runtime complexity?
 *
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 104
 * - 0 <= nums[i] <= n
 * - All the numbers of nums are
 *
 * Topics: Array, Hash Table, Math, Binary Search, Bit Manipulation, Sorting
 * Time Complexity: O(log n)
 * Space Complexity: O(n)
 */

class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> vis = new HashSet<>();
        PriorityQueue<Long> q = new PriorityQueue<>();
        int[] f = new int[] {2,3, 5};
        q.offer(1L);
        vis.add(1L);
        long ans = 0;
        while (n-- > 0) {
            ans = q.poll();
            for (int v : f) {
                long next =ans * v;
                if (vis.add(next)) {
                    q.offer(next);
                }
            }
        }
        return (int) ans;
    }
}
