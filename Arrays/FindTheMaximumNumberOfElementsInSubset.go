// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subset that can be arranged into a
//   palindrome-like sequence where each element is the square of the
//   previous one until the middle, then squares in reverse. This is
//   essentially a chain of numbers where each step squares the previous
//   number. The pattern is symmetric: [x, x^2, x^4, ..., x^(2^k), ...,
//   x^4, x^2, x]. The length is 2k+1 for some k >= 0. For a given starting
//   number x, we can build the chain by repeatedly squaring as long as we
//   have at least two copies of each intermediate number (except possibly
//   the middle element which can have one copy). Special case: number 1,
//   because 1^2 = 1, so any number of 1's can form a chain of length
//   (count of 1's) but must be odd length (since pattern is symmetric). So
//   we handle 1 separately: the maximum odd length <= count of 1's. For
//   other numbers, we iterate over each distinct number as potential
//   start, count how many steps we can go while having at least 2 copies,
//   then add 1 if the final number (the middle) exists. We track the
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
        ans = ones - (ones % 2 ^ 1)
    }
    for x := range cnt {
        length := 0
        for cnt[x] > 1 {
            x = x * x
            length += 2
        }
        if _, ok := cnt[x]; ok {
            length++
        } else {
            length--
        }
        if length > ans {
            ans = length
        }
    }
    return ans
}
