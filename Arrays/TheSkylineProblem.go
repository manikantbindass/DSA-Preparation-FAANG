// ──────────────────────────────────────────────────────────────────────
// LeetCode #218 · The Skyline Problem
// Difficulty : Hard
// Topics     : Array, Divide and Conquer, Binary Indexed Tree, Segment Tree, Sweep Line, Sorting, Heap (Priority Queue), Ordered Set
// URL        : https://leetcode.com/problems/the-skyline-problem/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a sweep line algorithm. First, collect all unique x-coordinates
//   (left and right edges) from all buildings and sort them. Then iterate
//   through these x-coordinates in order. Maintain a max-heap (priority
//   queue) of active buildings, where each entry is a tuple (negative
//   height, left, right). For each x, add all buildings whose left edge ≤
//   current x to the heap. Remove buildings whose right edge ≤ current x
//   (they are no longer active). The current height is the max height
//   among active buildings (top of heap, negated). If the height changes
//   from the previous key point, add a new key point [x, current height].
//   This ensures no consecutive horizontal lines of equal height.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(n)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//     Output : [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//   Example 2:
//     Input  : buildings = [[0,2,3],[2,5,3]]
//     Output : [[0,3],[5,0]]
// 
// Constraints
//   · 1 <= buildings.length <= 104
//   · 0 <= lefti < righti <= 231 - 1
//   · 1 <= heighti <= 231 - 1
//   · buildings is sorted by lefti in non-decreasing order.
// ──────────────────────────────────────────────────────────────────────

import (
    "container/heap"
    "sort"
)

type Building struct {
    negHeight int
    left      int
    right     int
}

type MaxHeap []*Building

func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i].negHeight < h[j].negHeight }
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MaxHeap) Push(x interface{}) {
    *h = append(*h, x.(*Building))
}

func (h *MaxHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}

func getSkyline(buildings [][]int) [][]int {
    // collect all unique x coordinates
    xsMap := make(map[int]bool)
    for _, b := range buildings {
        xsMap[b[0]] = true
        xsMap[b[1]] = true
    }
    xs := make([]int, 0, len(xsMap))
    for x := range xsMap {
        xs = append(xs, x)
    }
    sort.Ints(xs)
    
    h := &MaxHeap{}
    heap.Init(h)
    result := make([][]int, 0)
    idx := 0
    n := len(buildings)
    for _, x := range xs {
        // add all buildings starting at or before x
        for idx < n && buildings[idx][0] <= x {
            b := &Building{
                negHeight: -buildings[idx][2],
                left:      buildings[idx][0],
                right:     buildings[idx][1],
            }
            heap.Push(h, b)
            idx++
        }
        // remove buildings that end at or before x
        for h.Len() > 0 && (*h)[0].right <= x {
            heap.Pop(h)
        }
        curHeight := 0
        if h.Len() > 0 {
            curHeight = -(*h)[0].negHeight
        }
        if len(result) == 0 || result[len(result)-1][1] != curHeight {
            result = append(result, []int{x, curHeight})
        }
    }
    return result
}
