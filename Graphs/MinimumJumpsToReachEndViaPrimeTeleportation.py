# LeetCode 3629 - Minimum Jumps to Reach End via Prime Teleportation
# Time Complexity: O(n log log M + n * d) | Space Complexity: O(M + n)
from collections import defaultdict, deque
from typing import Deque, Dict, List


LIMIT = 1_000_001
FACTORS = [[] for _ in range(LIMIT)]

for factor in range(2, LIMIT):
    if not FACTORS[factor]:
        for multiple in range(factor, LIMIT, factor):
            FACTORS[multiple].append(factor)


class Solution:
    def minJumps(self, nums: List[int]) -> int:
        groups: Dict[int, List[int]] = defaultdict(list)
        for index, value in enumerate(nums):
            for factor in FACTORS[value]:
                groups[factor].append(index)

        n = len(nums)
        visited = [False] * n
        visited[0] = True
        queue: Deque[int] = deque([0])
        jumps = 0

        while queue:
            for _ in range(len(queue)):
                index = queue.popleft()
                if index == n - 1:
                    return jumps

                if index + 1 < n and not visited[index + 1]:
                    visited[index + 1] = True
                    queue.append(index + 1)
                if index - 1 >= 0 and not visited[index - 1]:
                    visited[index - 1] = True
                    queue.append(index - 1)

                next_indices = groups.get(nums[index])
                if next_indices is None:
                    continue

                for next_index in next_indices:
                    if not visited[next_index]:
                        visited[next_index] = True
                        queue.append(next_index)

                next_indices.clear()

        return -1
