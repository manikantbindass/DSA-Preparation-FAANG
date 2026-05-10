# LeetCode 2770 - Maximum Number of Jumps to Reach the Last Index
# Time Complexity: O(n^2) | Space Complexity: O(n)
from typing import List


class Solution:
    def maximumJumps(self, nums: List[int], target: int) -> int:
        n = len(nums)
        memo: list[int | None] = [None] * n

        def dfs(index: int) -> int:
            if index == n - 1:
                return 0

            if memo[index] is not None:
                return memo[index]

            answer = -(1 << 30)
            for next_index in range(index + 1, n):
                if abs(nums[index] - nums[next_index]) <= target:
                    answer = max(answer, 1 + dfs(next_index))

            memo[index] = answer
            return answer

        answer = dfs(0)
        return -1 if answer < 0 else answer
