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

import (
    "sort"
    "strconv"
    "strings"
)

func largestNumber(nums []int) string {
    strs := make([]string, len(nums))
    for i, v := range nums {
        strs[i] = strconv.Itoa(v)
    }
    sort.Slice(strs, func(i, j int) bool {
        return strs[i]+strs[j] > strs[j]+strs[i]
    })
    if strs[0] == "0" {
        return "0"
    }
    return strings.Join(strs, "")
}
