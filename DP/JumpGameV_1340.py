# 1340. Jump Game V
# https://leetcode.com/problems/jump-game-v/
# Difficulty: Hard

from typing import List

class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        self.arr = arr
        self.d = d
        self.f = [None] * n
        
        def dfs(i: int) -> int:
            if self.f[i] is not None:
                return self.f[i]
            ans = 1
            # jump left
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            # jump right
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            self.f[i] = ans
            return ans
        
        result = 1
        for i in range(n):
            result = max(result, dfs(i))
        return result
