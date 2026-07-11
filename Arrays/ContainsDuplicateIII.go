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

import "math"

func containsNearbyAlmostDuplicate(nums []int, indexDiff int, valueDiff int) bool {
    // Use a balanced BST via a custom implementation or use a simple approach with buckets
    // For simplicity, we use a sliding window with a sorted slice (inefficient but works for small constraints)
    // In production, consider using a balanced BST library or bucket approach.
    // Here we implement a bucket-based solution for O(n) average.
    if valueDiff < 0 {
        return false
    }
    buckets := make(map[int64]int64)
    for i, num := range nums {
        bucket := int64(num) / int64(valueDiff+1)
        if num < 0 {
            bucket--
        }
        if _, exists := buckets[bucket]; exists {
            return true
        }
        if val, exists := buckets[bucket-1]; exists && int64(num)-val <= int64(valueDiff) {
            return true
        }
        if val, exists := buckets[bucket+1]; exists && val-int64(num) <= int64(valueDiff) {
            return true
        }
        buckets[bucket] = int64(num)
        if i >= indexDiff {
            oldNum := int64(nums[i-indexDiff])
            oldBucket := oldNum / int64(valueDiff+1)
            if oldNum < 0 {
                oldBucket--
            }
            delete(buckets, oldBucket)
        }
    }
    return false
}
