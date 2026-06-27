// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subsequence that can be arranged into a
//   palindrome-like pattern where each element is the square of the
//   previous one until the middle, then mirrored. The pattern is
//   essentially a sequence that starts with some number x, then each next
//   element is the square of the previous, until we reach a peak, then we
//   go back down by taking square roots. This is equivalent to finding
//   chains where each number is the square of the previous, and we can use
//   duplicates to form the symmetric part. We count frequencies of each
//   number. For 1, since 1^2 = 1, we can only use it in a special way: the
//   longest chain using only 1s is either all 1s (if odd count) or all but
//   one (if even count) because we need a single peak. For other numbers,
//   we start from a number and repeatedly square it as long as we have at
//   least two copies (to use both sides of the palindrome). When we hit a
//   number that appears only once, we can use it as the center. We also
//   consider the possibility of stopping earlier if the next square
//   doesn't exist. We track the maximum length found.
// 
// Complexity
//   Time  : O(n log M) where M is max value, due to repeated squaring
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
        cur := x
        for cnt[cur] > 1 {
            length += 2
            cur = cur * cur
        }
        if v, ok := cnt[cur]; ok {
            length += v
        } else {
            length--
        }
        if length > ans {
            ans = length
        }
    }
    return ans
}
