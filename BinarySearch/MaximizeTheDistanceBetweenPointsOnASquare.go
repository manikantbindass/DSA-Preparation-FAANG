// LeetCode 3464 - Maximize the Distance Between Points on a Square
// Time Complexity: O(n log side) | Space Complexity: O(n)
package main

import "sort"

type Sequence struct {
	sx, sy int
	ex, ey int
	len    int
}

func maxDistance(side int, points [][]int, k int) int {
	ordered := getOrderedPoints(side, points)

	low, high := 0, side
	for low < high {
		mid := (low + high + 1) >> 1
		if isValid(ordered, k, mid) {
			low = mid
		} else {
			high = mid - 1
		}
	}
	return low
}

func isValid(ordered [][]int, k, d int) bool {
	n := len(ordered)
	dq := make([]Sequence, 0, n)

	first := ordered[0]
	dq = append(dq, Sequence{first[0], first[1], first[0], first[1], 1})

	maxLen := 1

	for i := 1; i < n; i++ {
		x, y := ordered[i][0], ordered[i][1]

		startX, startY := x, y
		length := 1

		for len(dq) > 0 &&
			abs(x-dq[0].ex)+abs(y-dq[0].ey) >= d {

			curr := dq[0]
			dq = dq[1:]

			if abs(x-curr.sx)+abs(y-curr.sy) >= d &&
				curr.len+1 >= length {

				startX = curr.sx
				startY = curr.sy
				length = curr.len + 1

				if length > maxLen {
					maxLen = length
				}
			}
		}

		dq = append(dq, Sequence{startX, startY, x, y, length})
	}

	return maxLen >= k
}

func getOrderedPoints(side int, points [][]int) [][]int {
	left := make([][]int, 0)
	top := make([][]int, 0)
	right := make([][]int, 0)
	bottom := make([][]int, 0)

	for _, p := range points {
		x, y := p[0], p[1]

		if x == 0 && y > 0 {
			left = append(left, p)
		} else if x > 0 && y == side {
			top = append(top, p)
		} else if x == side && y < side {
			right = append(right, p)
		} else {
			bottom = append(bottom, p)
		}
	}

	sort.Slice(left, func(i, j int) bool { return left[i][1] < left[j][1] })
	sort.Slice(top, func(i, j int) bool { return top[i][0] < top[j][0] })
	sort.Slice(right, func(i, j int) bool { return right[i][1] > right[j][1] })
	sort.Slice(bottom, func(i, j int) bool { return bottom[i][0] > bottom[j][0] })

	ordered := make([][]int, 0, len(points))
	ordered = append(ordered, left...)
	ordered = append(ordered, top...)
	ordered = append(ordered, right...)
	ordered = append(ordered, bottom...)

	return ordered
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}
