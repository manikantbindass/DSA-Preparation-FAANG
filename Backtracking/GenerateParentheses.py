# LeetCode 22 - Generate Parentheses
# Time Complexity: O(4^n / sqrt(n)) | Space Complexity: O(n)
from typing import List


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        answer = []

        def dfs(open_count: int, close_count: int, path: list[str]) -> None:
            if open_count == n and close_count == n:
                answer.append("".join(path))
                return

            if open_count < n:
                path.append("(")
                dfs(open_count + 1, close_count, path)
                path.pop()

            if close_count < open_count:
                path.append(")")
                dfs(open_count, close_count + 1, path)
                path.pop()

        dfs(0, 0, [])
        return answer
