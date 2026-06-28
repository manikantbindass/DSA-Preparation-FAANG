// ──────────────────────────────────────────────────────────────────────
// LeetCode #0 · minimum-time-to-reach-target-with-limited-power
// Difficulty : Medium
// Topics     : N/A
// URL        : https://leetcode.com/problems/minimum-time-to-reach-target-with-limited-power/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem is to find the maximum subarray sum after optionally
//   multiplying or dividing one element by k. The solution uses prefix and
//   suffix maximum subarray sums (Kadane's algorithm) to compute the best
//   subarray that includes a modified element. For each index i, we
//   consider two cases: multiply nums[i] by k or divide by k (integer
//   division). We compute the best subarray ending at i (left side) and
//   starting at i (right side), then combine them. The answer is the
//   maximum over all i and both operations.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 1 ms
// Memory   : 42.7 MB
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

func maxSubarraySum(nums []int, k int) int64 {
    n := len(nums)
    suf := make([]int64, n)
    pre := make([]int64, n)
    suf[0] = int64(nums[0])
    for i := 1; i < n; i++ {
        if int64(nums[i]) > suf[i-1]+int64(nums[i]) {
            suf[i] = int64(nums[i])
        } else {
            suf[i] = suf[i-1] + int64(nums[i])
        }
    }
    pre[n-1] = int64(nums[n-1])
    for i := n - 2; i >= 0; i-- {
        if int64(nums[i]) > pre[i+1]+int64(nums[i]) {
            pre[i] = int64(nums[i])
        } else {
            pre[i] = pre[i+1] + int64(nums[i])
        }
    }
    lft := make([]int64, n)
    rgt := make([]int64, n)
    for i := 0; i < n; i++ {
        if suf[i] > 0 {
            lft[i] = suf[i]
        } else {
            lft[i] = 0
        }
        if pre[i] > 0 {
            rgt[i] = pre[i]
        } else {
            rgt[i] = 0
        }
    }
    best := func(mul bool) int64 {
        top := int64(-1 << 62)
        cur := int64(0)
        for i := 0; i < n; i++ {
            var val int64
            if mul {
                val = int64(nums[i]) * int64(k)
            } else {
                val = int64(nums[i]) / int64(k)
            }
            var ext int64
            if i == 0 {
                ext = 0
            } else {
                if cur > lft[i-1] {
                    ext = cur
                } else {
                    ext = lft[i-1]
                }
            }
            cur = val + ext
            var tail int64
            if i+1 < n {
                tail = rgt[i+1]
            } else {
                tail = 0
            }
            if cur+tail > top {
                top = cur + tail
            }
        }
        return top
    }
    ans := best(true)
    if best(false) > ans {
        ans = best(false)
    }
    return ans
}
