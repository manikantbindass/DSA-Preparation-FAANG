// ──────────────────────────────────────────────────────────────────────
// LeetCode #3919 · Network Recovery Pathways
// Difficulty : Hard
// Topics     : Array, Binary Search, Dynamic Programming, Graph Theory, Topological Sort, Heap (Priority Queue), Shortest Path
// URL        : https://leetcode.com/problems/network-recovery-pathways/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We need to find the maximum possible minimum edge cost along any path
//   from node 0 to node n-1 that stays within total cost k and uses only
//   online intermediate nodes. Since the graph is a DAG, we can binary
//   search on the minimum edge cost threshold. For a candidate threshold
//   mid, we consider only edges with cost >= mid and run Dijkstra (or any
//   shortest path) to check if there exists a path from 0 to n-1 with
//   total cost <= k. The binary search finds the largest mid for which
//   such a path exists. If no path exists even with the smallest possible
//   threshold, return -1.
// 
// Complexity
//   Time  : O((n + m) log n log C) where C is the range of edge costs
//   Space : O(n + m)
// 
// Runtime  : 4 ms
// Memory   : 42.6 MB
// 
// Examples
//   Example 1:
//     Input  : edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
//     Output : 3
//   Example 2:
//     Input  : edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
//     Output : 6
// 
// Constraints
//   · n == online.length
//   · 2 <= n <= 5 * 104
//   · 0 <= m == edges.length <= min(105, n * (n - 1) / 2)
//   · edges[i] = [ui, vi, costi]
//   · 0 <= ui, vi < n
//   · ui != vi
//   · 0 <= costi <= 109
//   · 0 <= k <= 5 * 1013
//   · online[i] is either true or false, and both online[0] and online[n &minus; 1] are true.
//   · The given graph is a directed acyclic graph.
// ──────────────────────────────────────────────────────────────────────

package main

import (
	"container/heap"
	"math"
)

type Item struct {
	dist int64
	node int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].dist < pq[j].dist }
func (pq PriorityQueue) Swap(i, j int) { pq[i], pq[j] = pq[j], pq[i] }
func (pq *PriorityQueue) Push(x interface{}) { *pq = append(*pq, x.(*Item)) }
func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	*pq = old[0 : n-1]
	return item
}

func findMaxPathScore(edges [][]int, online []bool, k int64) int {
	n := len(online)
	g := make([][][2]int, n)
	minCost := math.MaxInt32
	maxCost := 0
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		if online[u] && online[v] {
			g[u] = append(g[u], [2]int{v, w})
			if w < minCost {
				minCost = w
			}
			if w > maxCost {
				maxCost = w
			}
		}
	}

	check := func(mid int) bool {
		dist := make([]int64, n)
		for i := range dist {
			dist[i] = math.MaxInt64 / 4
		}
		dist[0] = 0
		pq := &PriorityQueue{}
		heap.Push(pq, &Item{dist: 0, node: 0})
		for pq.Len() > 0 {
			cur := heap.Pop(pq).(*Item)
			d, u := cur.dist, cur.node
			if d > k {
				return false
			}
			if u == n-1 {
				return true
			}
			if dist[u] < d {
				continue
			}
			for _, edge := range g[u] {
				v, w := edge[0], edge[1]
				if w < mid {
					continue
				}
				nd := d + int64(w)
				if nd < dist[v] {
					dist[v] = nd
					heap.Push(pq, &Item{dist: nd, node: v})
				}
			}
		}
		return false
	}

	if !check(minCost) {
		return -1
	}
	lo, hi := minCost, maxCost
	for lo < hi {
		mid := (lo + hi + 1) / 2
		if check(mid) {
			lo = mid
		} else {
			hi = mid - 1
		}
	}
	return lo
}
