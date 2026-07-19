/*
 * LeetCode Problem 0: count-dominant-nodes-in-a-binary-tree
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-dominant-nodes-in-a-binary-tree/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.9 MB
 */

class Solution {
    public boolean canReach(int[] start, int[] target) {
        return ((start[0]+ start[1]) & 1)== ((target[0] + target[1]) &1);
    }
}
