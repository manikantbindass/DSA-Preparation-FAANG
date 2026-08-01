/*
 * LeetCode Problem 0: widest-possible-fence
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/widest-possible-fence/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 42.6 MB
 */

class Solution {
    public int countValidPrefixes(String s) {
        int z =0,o=0,a = 0;
        for (int i =0; i<s.length(); i++) {
            if (s.charAt(i)== '0') z++;
            else o++;
            if (Math.abs(z-o) <= 1) a++;
        }
        return a;
    }
}
