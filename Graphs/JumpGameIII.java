/*
 * LeetCode Problem 1306: Jump Game III
 * Problem Number: 1306
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/jump-game-iii/
 * 
 * Given an array of non-negative integers arr, you are initially positioned at start index.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], 
 * provided the destination index is within bounds.
 * 
 * Return true if you can reach any index with value 0, otherwise return false.
 * 
 * Example 1:
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation: 
 * Index 5 -> index 4 (5 - 1) -> index 1 (4 - 3) -> index 3 (1 + 2) which has value 0.
 * 
 * Example 2:
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation: Index 0 -> index 4 (0 + 4) -> index 1 (4 - 3) -> index 3 (1 + 2) has value 0.
 * 
 * Example 3:
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: Cannot reach index with value 0.
 * 
 * Constraints:
 * - 1 <= arr.length <= 5 * 10^4
 * - 0 <= arr[i] < arr.length
 * - 0 <= start < arr.length
 * 
 * Topics: Array, Depth-First Search, Breadth-First Search
 * Time Complexity: O(n) - each index is visited at most once
 * Space Complexity: O(n) - for the queue and visited array
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean canReach(int[] arr, int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int i = queue.poll();
            
            // Found a zero value
            if (arr[i] == 0) {
                return true;
            }
            
            int jump = arr[i];
            // Mark as visited by setting to -1 (since arr[i] >= 0 normally)
            arr[i] = -1;
            
            // Explore both directions
            for (int next : List.of(i + jump, i - jump)) {
                if (next >= 0 && next < arr.length && arr[next] >= 0) {
                    queue.offer(next);
                }
            }
        }
        
        return false;
    }
}
