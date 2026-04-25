# LeetCode 3464 - Maximize the Distance Between Points on a Square
# Time Complexity: O(n log side) | Space Complexity: O(n)
from collections import deque
from dataclasses import dataclass
from typing import List


@dataclass(frozen=True)
class Sequence:
    sx: int
    sy: int
    ex: int
    ey: int
    length: int


class Solution:
    def maxDistance(self, side: int, points: List[List[int]], k: int) -> int:
        ordered = self._get_ordered_points(side, points)

        low, high = 0, side
        while low < high:
            mid = (low + high + 1) // 2
            if self._is_valid(ordered, k, mid):
                low = mid
            else:
                high = mid - 1

        return low

    def _is_valid(self, ordered: List[List[int]], k: int, d: int) -> bool:
        first = ordered[0]
        dq = deque([Sequence(first[0], first[1], first[0], first[1], 1)])
        max_len = 1

        for x, y in ordered[1:]:
            start_x, start_y = x, y
            length = 1

            while dq and self._distance(x, y, dq[0].ex, dq[0].ey) >= d:
                curr = dq.popleft()

                if self._distance(x, y, curr.sx, curr.sy) >= d and curr.length + 1 >= length:
                    start_x = curr.sx
                    start_y = curr.sy
                    length = curr.length + 1
                    max_len = max(max_len, length)

            dq.append(Sequence(start_x, start_y, x, y, length))

        return max_len >= k

    def _get_ordered_points(self, side: int, points: List[List[int]]) -> List[List[int]]:
        left = []
        top = []
        right = []
        bottom = []

        for point in points:
            x, y = point

            if x == 0 and y > 0:
                left.append(point)
            elif x > 0 and y == side:
                top.append(point)
            elif x == side and y < side:
                right.append(point)
            else:
                bottom.append(point)

        left.sort(key=lambda point: point[1])
        top.sort(key=lambda point: point[0])
        right.sort(key=lambda point: point[1], reverse=True)
        bottom.sort(key=lambda point: point[0], reverse=True)

        return left + top + right + bottom

    def _distance(self, x1: int, y1: int, x2: int, y2: int) -> int:
        return abs(x1 - x2) + abs(y1 - y2)
