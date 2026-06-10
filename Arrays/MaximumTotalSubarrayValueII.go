/*
LeetCode Problem 3691: Maximum Total Subarray Value II
Problem Number: 3691
Difficulty: Hard
Link: https://leetcode.com/problems/maximum-total-subarray-value-ii/

Given an array nums and an integer k, find the maximum total value by selecting k subarrays
where the value of a subarray is defined as its max - min.

Example:
Input: nums = [1,2,3,4,5], k = 2
Output: 8

Constraints:
- 1 <= nums.length <= 10^5
- 1 <= nums[i] <= 10^9
- 1 <= k <= 10^5

Topics: Array, Sparse Table, Priority Queue, Greedy
Time Complexity: O(n log n + k log n) - building sparse table and processing k operations
Space Complexity: O(n log n) - for the sparse table
*/

package arrays

import "container/heap"

type Item struct {
    value int
    left  int
    right int
}

type MaxHeap []Item

func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i].value > h[j].value }
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MaxHeap) Push(x interface{}) {
    *h = append(*h, x.(Item))
}

func (h *MaxHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}

type SparseTable struct {
    pow      []int
    maxTable [][]int
    minTable [][]int
}

func NewSparseTable(arr []int) *SparseTable {
    n := len(arr)
    st := &SparseTable{}
    // Precompute powers of 2
    st.pow = make([]int, n+1)
    for i := 2; i <= n; i++ {
        st.pow[i] = st.pow[i>>1] + 1
    }
    
    maxLog := st.pow[n]
    st.maxTable = make([][]int, maxLog+1)
    st.minTable = make([][]int, maxLog+1)
    for i := 0; i <= maxLog; i++ {
        st.maxTable[i] = make([]int, n)
        st.minTable[i] = make([]int, n)
    }
    
    // Initialize level 0
    copy(st.maxTable[0], arr)
    copy(st.minTable[0], arr)
    
    // Build sparse table
    for p := 1; p <= maxLog; p++ {
        length := n - (1 << p)
        prevLen := 1 << (p - 1)
        for i := 0; i <= length; i++ {
            st.maxTable[p][i] = max(st.maxTable[p-1][i+prevLen], st.maxTable[p-1][i])
            st.minTable[p][i] = min(st.minTable[p-1][i+prevLen], st.minTable[p-1][i])
        }
    }
    
    return st
}

func (st *SparseTable) Query(left, right int) int {
    p := st.pow[right-left+1]
    maxVal := max(st.maxTable[p][right-(1<<p)+1], st.maxTable[p][left])
    minVal := min(st.minTable[p][right-(1<<p)+1], st.minTable[p][left])
    return maxVal - minVal
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func maxTotalValue(nums []int, k int) int64 {
    n := len(nums)
    st := NewSparseTable(nums)
    pq := &MaxHeap{}
    heap.Init(pq)
    
    heap.Push(pq, Item{value: st.Query(0, n-1), left: 0, right: n - 1})
    var total int64 = 0
    
    for i := 0; i < k; i++ {
        item := heap.Pop(pq).(Item)
        total += int64(item.value)
        
        if item.left < item.right {
            heap.Push(pq, Item{value: st.Query(item.left, item.right-1), left: item.left, right: item.right - 1})
            heap.Push(pq, Item{value: st.Query(item.left+1, item.right), left: item.left + 1, right: item.right})
        }
    }
    
    return total
}
