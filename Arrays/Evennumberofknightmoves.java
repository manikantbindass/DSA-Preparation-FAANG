/*
 * LeetCode Problem 0: even-number-of-knight-moves
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/even-number-of-knight-moves/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    public boolean canReach(int[] start, int[] target) {
        return ((start[0]+ start[1]) & 1)== ((target[0] + target[1]) &1);
    }
}
