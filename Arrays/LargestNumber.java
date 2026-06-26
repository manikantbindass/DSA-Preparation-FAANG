// ──────────────────────────────────────────────────────────────────────
// LeetCode #179 · Largest Number
// Difficulty : Medium
// Topics     : Array, String, Greedy, Sorting
// URL        : https://leetcode.com/problems/largest-number/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to arrange a list of non-negative integers to form the
//   largest possible number. The key insight is to use a custom comparator
//   for sorting: for two strings a and b, we compare a+b vs b+a. If a+b >
//   b+a, then a should come before b. After sorting, if the first element
//   is "0", the entire number is zero. Otherwise, we concatenate the
//   sorted strings. This greedy sorting approach works because the
//   comparator ensures the optimal ordering.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(n)
// 
// Runtime  : 8
// Memory   : 44964000
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {
        List<String> vs = new ArrayList<>();
        for (int v : nums) {
            vs.add(String.valueOf(v));
        }
        vs.sort((a, b) -> (b + a).compareTo(a + b));
        if (vs.get(0).equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : vs) {
            sb.append(s);
        }
        return sb.toString();
    }
}
