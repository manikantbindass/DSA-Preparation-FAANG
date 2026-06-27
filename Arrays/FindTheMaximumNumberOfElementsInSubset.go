// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subsequence that can be arranged into a
//   palindrome-like pattern where each element is the square of the
//   previous one from the center outward. The pattern is symmetric: [x,
//   x^2, x^4, ..., x^(2^k), ..., x^4, x^2, x]. This means we can think of
//   building sequences by repeatedly squaring numbers. We count
//   frequencies of each number. For 1, since 1^2 = 1, we can use all 1s
//   but the sequence must be symmetric; the maximum length using only 1s
//   is the largest odd number ≤ count(1). For other numbers, we start from
//   a base x and repeatedly square while we have at least 2 copies of the
//   current number (to place on both sides). When we can no longer square
//   (either because the next square is missing or we have only 1 copy), we
//   add 1 if we have at least one copy of the final number. We track the
//   maximum length found.
// 
// Complexity
//   Time  : O(n log log maxVal)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : nums = [5,4,1,2,2]
//     Output : 3
//     Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.
//   Example 2:
//     Input  : nums = [1,3,2,4]
//     Output : 1
//     Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer.
// 
// Constraints
//   · 2 <= nums.length <= 105
//   · 1 <= nums[i] <= 109
// ──────────────────────────────────────────────────────────────────────

func maximumLength(nums []int) int {
    cnt := make(map[int]int)
    for _, x := range nums {
        cnt[x]++
    }
    ones := cnt[1]
    delete(cnt, 1)
    ans := 0
    if ones > 0 {
        if ones%2 == 1 {
            ans = ones
        } else {
            ans = ones - 1
        }
    }
    for x := range cnt {
        length := 0
        for cnt[x] > 1 {
            x = x * x
            length += 2
        }
        if cnt[x] > 0 {
            length += 1
        } else {
            length -= 1
        }
        if length > ans {
            ans = length
        }
    }
    return ans
}
