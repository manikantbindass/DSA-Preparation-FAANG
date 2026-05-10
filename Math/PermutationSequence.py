# LeetCode 60 - Permutation Sequence
# Time Complexity: O(n^2) | Space Complexity: O(n)


class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        answer: list[str] = []
        visited = [False] * (n + 1)

        for index in range(n):
            factorial = 1
            for value in range(1, n - index):
                factorial *= value

            for value in range(1, n + 1):
                if not visited[value]:
                    if k > factorial:
                        k -= factorial
                    else:
                        answer.append(str(value))
                        visited[value] = True
                        break

        return "".join(answer)
