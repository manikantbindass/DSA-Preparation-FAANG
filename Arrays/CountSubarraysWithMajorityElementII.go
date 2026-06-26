/*
LeetCode Problem 3739: Count Subarrays With Majority Element II
Problem Number: 3739
Difficulty: Medium
Link: https://leetcode.com/problems/count-subarrays-with-majority-element-ii/

Given an array nums and an integer target, return the number of subarrays where the
majority element (appearing more than half the subarray length) is the target.

Example:
Input: nums = [1,2,1,1], target = 1
Output: 3

Constraints:
- 1 <= nums.length <= 10^5
- 0 <= nums[i] <= 10^9
- 0 <= target <= 10^9

Topics: Array, Binary Indexed Tree, Prefix Sum
Time Complexity: O(n log n) - for Fenwick Tree operations
Space Complexity: O(n) - for the BIT array
*/

package arrays

type BinaryIndexedTree struct {
    n    int
    tree []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
    return &BinaryIndexedTree{
        n:    n,
        tree: make([]int, n+1),
    }
}

func (bit *BinaryIndexedTree) Update(index int, delta int) {
    for index <= bit.n {
        bit.tree[index] += delta
        index += index & -index
    }
}

func (bit *BinaryIndexedTree) Query(index int) int {
    sum := 0
    for index > 0 {
        sum += bit.tree[index]
        index -= index & -index
    }
    return sum
}

func countMajoritySubarrays(nums []int, target int) int64 {
    n := len(nums)
    bit := NewBinaryIndexedTree(2*n + 1)
    
    // Offset to handle negative indices
    prefix := n + 1
    bit.Update(prefix, 1)
    var result int64 = 0
    
    for _, num := range nums {
        // Increment prefix if num is target, otherwise decrement
        if num == target {
            prefix++
        } else {
            prefix--
        }
        // Count previous prefixes less than current prefix
        result += int64(bit.Query(prefix - 1))
        // Add current prefix to BIT
        bit.Update(prefix, 1)
    }
    
    return result
}
