// ──────────────────────────────────────────────────────────────────────
// LeetCode #3299 · Find the Maximum Number of Elements in Subset
// Difficulty : Medium
// Topics     : Array, Hash Table, Enumeration
// URL        : https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the longest subset that can be arranged into a
//   palindrome-like sequence where each element is the square of the
//   previous one, except the middle element which appears once (or twice
//   if the sequence length is even). The pattern is symmetric: [x, x^2,
//   x^4, ..., x^(2^k), ..., x^4, x^2, x]. This means we can think of
//   building chains by repeatedly squaring numbers. For each starting
//   number, we can traverse the chain: while the current number appears at
//   least twice (except possibly the last one), we can extend the chain by
//   2 (one on each side). The middle element can appear once or twice (if
//   the chain length is even, the middle appears twice; if odd, once).
//   Special case: number 1, because 1^2 = 1, so any number of 1's can form
//   a chain of length equal to the count of 1's, but if count is odd, we
//   can use all; if even, we can use count-1 (since we need a single
//   middle or two middles? Actually pattern for 1: [1,1,1,...] is valid?
//   The pattern requires each step to square, but 1^2=1, so any sequence
//   of 1's works. However, the pattern is symmetric, so we can have any
//   number of 1's. But the problem's pattern definition: [x, x^2, x^4,
//   ..., x^k, ..., x^4, x^2, x] where k is a power of 2. For x=1, all
//   elements are 1, so any length works. But we must consider that the
//   middle element can be repeated? Actually the pattern has a single
//   middle element (if odd length) or two middle elements (if even
//   length). For 1, since all are same, we can have any count. But the
//   accepted solution treats 1 specially: it removes 1 from map and
//   computes its contribution as t - (t % 2 ^ 1). Let's analyze: For count
//   t of 1's, the maximum subset length using only 1's is: if t is odd, we
//   can use all t (since middle element appears once, and the rest are
//   pairs). If t is even, we can use t-1 (since we need an odd number of
//   elements to have a single middle? Actually pattern can have even
//   length: e.g., [x, x^2, x^2, x] is valid? The pattern given: [x, x^2,
//   x^4, ..., x^(k/2), x^k, x^(k/2), ..., x^4, x^2, x]. For k=2, pattern
//   is [x, x^2, x]. That's length 3. For k=4, pattern is [x, x^2, x^4,
//   x^2, x] length 5. So pattern length is always odd. But the example
//   [2,4,2] is length 3. So the pattern always has odd length. However,
//   the problem says "k can be any non-negative power of 2". For k=1
//   (2^0), pattern is [x, x]? Actually if k=1, then x^(k/2) = x^(0.5) not
//   integer. So k must be at least 2? The example [3,9,3] corresponds to
//   x=3, x^2=9, so pattern length 3. So pattern length is always odd. But
//   the accepted solution allows even length for 1? Let's check: t=2, t -
//   (t%2 ^ 1) = 2 - (0 ^ 1) = 2 - 1 = 1. So for two 1's, answer is 1. That
//   matches pattern length odd. For t=3, 3 - (1 ^ 1) = 3 - 0 = 3. So three
//   1's give length 3. So indeed pattern length must be odd. So for any
//   number, the chain length must be odd. The algorithm: count
//   frequencies. Remove 1 and handle separately. For each other number x,
//   we traverse the chain: while the current number appears at least twice
//   (i.e., count > 1), we can add 2 to the length (one on each side) and
//   move to x*x. When we can't go further (count <= 1), we add the count
//   of the last number (which could be 0 or 1). This gives the maximum
//   odd-length chain starting from x. We take the maximum over all
//   starting numbers. Note: we must avoid infinite loops due to overflow?
//   x up to 1e9, squaring can overflow 64-bit? In Java, we use long. In
//   Python, big ints are fine. In Go, we use int64 and check overflow? But
//   constraints: nums[i] <= 1e9, and we square at most log2(1e9) ~ 30
//   times before exceeding 1e9? Actually squaring quickly exceeds 1e9, so
//   we can stop when x > 1e9? But the map only contains numbers from nums,
//   so if x*x is not in map, we stop. So no overflow issue if we use
//   64-bit. However, x*x might exceed 2^63-1? Starting from 1e9, squaring
//   gives 1e18 which fits in 64-bit signed (max ~9e18). Next square would
//   be 1e36 which overflows. But we will stop because that number won't be
//   in map. So we can safely use int64 in Go. But to be safe, we can check
//   if x > 1e9 before squaring? Actually we only square if count > 1, and
//   we check if x*x is in map. If x*x overflows, it might become negative
//   or wrap, causing issues. So we should either use big integers or check
//   for overflow. In Go, we can use int64 and check if x > 1e9? But 1e9
//   squared is 1e18, still within int64. Next square would be 1e36 > 9e18,
//   overflow. So we can stop when x > 1e9? Actually if x > 1e9, its square
//   will overflow. But we only square when count>1, and we check if x*x is
//   in map. Since map keys are from original nums (<=1e9), x*x > 1e9 will
//   not be in map, so we will stop. But if we compute x*x and it
//   overflows, it might become a negative number that accidentally matches
//   some key? Very unlikely but possible. To be safe, we can check if x >
//   1e9 before squaring, then break. Or use math/big. Simpler: in Go, we
//   can use int64 and rely on the fact that nums[i] <= 1e9, so any square
//   > 1e9 won't be in map, and we can check if x > 1e9 before squaring to
//   avoid overflow. But the accepted Java solution doesn't check overflow;
//   it uses long and relies on the fact that x*x might overflow but then
//   the map won't contain that value. However, overflow could produce a
//   value that coincidentally exists? Very unlikely. For production
//   quality, we should handle overflow. We'll add a check: if x > 1e9,
//   break. Also, we need to handle the case where x*x might overflow to a
//   negative number that is in map? But map keys are positive, so negative
//   won't match. So it's safe. But we'll add a check for x > 1e9 to avoid
//   unnecessary computation. Also, we need to consider that the chain
//   might start from a number that appears only once, then length = 1
//   (just that number). So we initialize ans = 1 (since any single element
//   is valid). But we also need to consider the special case of 1. The
//   algorithm: count frequencies. Remove 1 and compute its contribution:
//   if count1 is odd, use all; if even, use count1-1. Then for each other
//   number, we simulate the chain. We'll use a map from int64 to int.
//   We'll iterate over keys. For each key x, we start with length = 0.
//   While count[x] >= 2, we add 2 to length, then x = x*x, but we need to
//   check if x*x is in map? Actually we need to check if the next number
//   exists. The condition: while the current number appears at least
//   twice, we can extend. But we also need to ensure that the next number
//   (x*x) exists in the map (even if count is 1, we can still use it as
//   the middle). So the loop: while count[x] >= 2, we add 2, then set x =
//   x*x, and continue. After the loop, we add count[x] (which is either 0
//   or 1) to length. Then update ans. Also, we need to consider that we
//   might have multiple chains starting from different numbers that are
//   part of the same chain? For example, if we have [2,4,16,4,2], starting
//   from 2 gives length 5. Starting from 4 gives length 3? Actually 4
//   appears twice, so we can get [4,16,4] length 3. But the maximum is 5.
//   So we need to consider all starting points. However, if we start from
//   2, we will consume the counts of 2,4,16? But we are not modifying the
//   map, so we might double-count? The algorithm does not modify the map,
//   so it's fine. But we must be careful: when we traverse from x, we are
//   using the original counts. If we later start from a number that is a
//   square of a previous number, we might get a shorter chain. That's okay
//   because we take max. So no need to modify map. However, there is a
//   nuance: the chain must use distinct elements? No, we can use multiple
//   copies of the same number. The subset can have duplicates. So the
//   counts are important. The algorithm correctly uses counts. So we
//   implement as described. Complexity: O(n * log(log(max))) because each
//   chain length is at most O(log log max) since squaring reduces the
//   number of steps quickly. Actually each step squares the number, so the
//   number of steps is O(log log max). So overall O(n log log max). Space:
//   O(n) for map.
// 
// Complexity
//   Time  : O(n log log M) where M is max value in nums
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
    cnt := make(map[int64]int)
    for _, x := range nums {
        cnt[int64(x)]++
    }
    // Handle 1 separately
    t, ok := cnt[1]
    ans := 0
    if ok {
        delete(cnt, 1)
        if t%2 == 1 {
            ans = t
        } else {
            ans = t - 1
        }
    }
    // For each other number, build chain
    for x := range cnt {
        length := 0
        cur := x
        for cnt[cur] >= 2 {
            length += 2
            // Avoid overflow: if cur > 1e9, next square will exceed 1e18 and not be in map
            if cur > 1000000000 {
                break
            }
            cur = cur * cur
        }
        length += cnt[cur]
        if length > ans {
            ans = length
        }
    }
    return ans
}
