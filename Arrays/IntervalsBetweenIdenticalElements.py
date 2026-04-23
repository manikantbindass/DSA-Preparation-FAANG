# LeetCode 2121 - Intervals Between Identical Elements
# Time Complexity: O(n) | Space Complexity: O(n)
from collections import defaultdict
from typing import List


class Solution:
    def getDistances(self, arr: List[int]) -> List[int]:
        groups = defaultdict(list)
        for index, value in enumerate(arr):
            groups[value].append(index)

        ans = [0] * len(arr)

        for indices in groups.values():
            m = len(indices)
            left = 0
            right = -m * indices[0]

            for index in indices:
                right += index

            for i, index in enumerate(indices):
                ans[index] = left + right

                if i + 1 < m:
                    gap = indices[i + 1] - index
                    left += gap * (i + 1)
                    right -= gap * (m - i - 1)

        return ans
