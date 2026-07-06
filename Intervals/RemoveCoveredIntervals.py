# ──────────────────────────────────────────────────────────────────────
# LeetCode #1222 · Remove Covered Intervals
# Difficulty : Medium
# Topics     : Array, Sorting
# URL        : https://leetcode.com/problems/remove-covered-intervals/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We sort intervals by start ascending, and if starts are equal, by end
#   descending. This ensures that when we iterate, any interval that
#   starts at the same point as a previous one will have a smaller or
#   equal end, so it is covered. Then we keep track of the maximum end
#   seen so far. For each interval, if its end is greater than the current
#   maximum end, it is not covered by any previous interval, so we
#   increment the count and update the maximum end. Otherwise, it is
#   covered and we skip it. Finally, we return the count of remaining
#   intervals.
# 
# Complexity
#   Time  : O(n log n)
#   Space : O(1) (excluding sorting space)
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : intervals = [[1,4],[3,6],[2,8]]
#     Output : 2
#     Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
#   Example 2:
#     Input  : intervals = [[1,4],[2,3]]
#     Output : 1
# 
# Constraints
#   · 1 <= intervals.length <= 1000
#   · intervals[i].length == 2
#   · 0 <= li < ri <= 105
#   · All the given intervals are
# ──────────────────────────────────────────────────────────────────────

from typing import List

class Solution:
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        count = 0
        max_end = float('-inf')
        for start, end in intervals:
            if end > max_end:
                count += 1
                max_end = end
        return count
