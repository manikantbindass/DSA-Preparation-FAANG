// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · Contains Duplicate III
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/contains-duplicate-iii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a sliding window of size indexDiff and maintain a balanced BST
//   (TreeSet in Java, sortedcontainers in Python, or a custom approach in
//   Go) to store the numbers in the current window. For each new number,
//   we check if there exists a number in the set that is within valueDiff
//   of the current number. Specifically, we find the smallest number >=
//   (nums[i] - valueDiff) and check if it is <= (nums[i] + valueDiff). If
//   such a number exists, we return true. Otherwise, we add the current
//   number to the set and remove the number that is indexDiff positions
//   behind (if the window size exceeds indexDiff). This ensures we only
//   consider pairs within the allowed index difference. The balanced BST
//   allows O(log k) operations where k is the window size.
// 
// Complexity
//   Time  : O(n log k) where k = indexDiff
//   Space : O(k)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            Long ceiling = set.ceiling(num - valueDiff);
            if (ceiling != null && ceiling <= num + valueDiff) {
                return true;
            }
            set.add(num);
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}
