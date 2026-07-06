// ──────────────────────────────────────────────────────────────────────
// LeetCode #1222 · Remove Covered Intervals
// Difficulty : Medium
// Topics     : Array, Sorting
// URL        : https://leetcode.com/problems/remove-covered-intervals/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We sort intervals by start ascending, and if starts are equal, by end
//   descending. This ensures that when we iterate, any interval that
//   starts at the same point as a previous one will have a smaller or
//   equal end, so it is covered. Then we keep track of the maximum end
//   seen so far. For each interval, if its end is greater than the current
//   maximum end, it is not covered by any previous interval, so we
//   increment the count and update the maximum end. Otherwise, it is
//   covered and we skip it. Finally, we return the count of remaining
//   intervals.
// 
// Complexity
//   Time  : O(n log n)
//   Space : O(1) (excluding sorting space)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : intervals = [[1,4],[3,6],[2,8]]
//     Output : 2
//     Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
//   Example 2:
//     Input  : intervals = [[1,4],[2,3]]
//     Output : 1
// 
// Constraints
//   · 1 <= intervals.length <= 1000
//   · intervals[i].length == 2
//   · 0 <= li < ri <= 105
//   · All the given intervals are
// ──────────────────────────────────────────────────────────────────────

import "sort"

func removeCoveredIntervals(intervals [][]int) int {
    sort.Slice(intervals, func(i, j int) bool {
        if intervals[i][0] == intervals[j][0] {
            return intervals[i][1] > intervals[j][1]
        }
        return intervals[i][0] < intervals[j][0]
    })
    count := 0
    maxEnd := -1 << 31
    for _, interval := range intervals {
        end := interval[1]
        if end > maxEnd {
            count++
            maxEnd = end
        }
    }
    return count
}
