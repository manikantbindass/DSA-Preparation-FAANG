# LeetCode 52 - N-Queens II
# Time Complexity: O(n!) | Space Complexity: O(n)


class Solution:
    def totalNQueens(self, n: int) -> int:
        columns = [False] * n
        diagonals = [False] * (n * 2)
        anti_diagonals = [False] * (n * 2)
        answer = 0

        def backtrack(row: int) -> None:
            nonlocal answer
            if row == n:
                answer += 1
                return

            for col in range(n):
                diagonal_index = row + col
                anti_diagonal_index = row - col + n
                if columns[col] or diagonals[diagonal_index] or anti_diagonals[anti_diagonal_index]:
                    continue

                columns[col] = True
                diagonals[diagonal_index] = True
                anti_diagonals[anti_diagonal_index] = True
                backtrack(row + 1)
                columns[col] = False
                diagonals[diagonal_index] = False
                anti_diagonals[anti_diagonal_index] = False

        backtrack(0)
        return answer
