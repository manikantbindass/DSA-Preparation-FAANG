/*
 * LeetCode Problem 2657: Find the Prefix Common Array of Two Arrays
 * Problem Number: 2657
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/
 * 
 * You are given two 0-indexed integer permutations A and B of length n.
 * A prefix common array of A and B is an array C such that C[i] is equal to the
 * number of elements that are present in the prefix A[0..i] and B[0..i].
 * 
 * Return the prefix common array of A and B.
 * 
 * Example 1:
 * Input: A = [1,3,2,4], B = [3,1,2,4]
 * Output: [0,2,3,4]
 * Explanation: 
 * i=0: Prefix A[0]=[1], B[0]=[3] -> common = 0
 * i=1: Prefix A[0..1]=[1,3], B[0..1]=[3,1] -> common = 2
 * i=2: Prefix A[0..2]=[1,3,2], B[0..2]=[3,1,2] -> common = 3
 * i=3: Prefix A[0..3]=[1,3,2,4], B[0..3]=[3,1,2,4] -> common = 4
 * 
 * Example 2:
 * Input: A = [2,3,1], B = [3,1,2]
 * Output: [0,1,3]
 * Explanation:
 * i=0: Prefix A[0]=[2], B[0]=[3] -> common = 0
 * i=1: Prefix A[0..1]=[2,3], B[0..1]=[3,1] -> common = 1
 * i=2: Prefix A[0..2]=[2,3,1], B[0..2]=[3,1,2] -> common = 3
 * 
 * Constraints:
 * - 1 <= n <= 50
 * - A.length == B.length == n
 * - A and B are permutations of [1, n]
 * 
 * Topics: Array, Hash Table
 * Time Complexity: O(n²) - straightforward implementation
 * Space Complexity: O(n) - for frequency arrays
 */

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int[] cnt1 = new int[n + 1];
        int[] cnt2 = new int[n + 1];
        
        for (int i = 0; i < n; ++i) {
            ++cnt1[A[i]];
            ++cnt2[B[i]];
            
            for (int j = 1; j <= n; ++j) {
                ans[i] += Math.min(cnt1[j], cnt2[j]);
            }
        }
        
        return ans;
    }
}
