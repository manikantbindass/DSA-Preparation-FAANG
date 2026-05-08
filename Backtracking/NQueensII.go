// LeetCode 52 - N-Queens II
// Time Complexity: O(n!) | Space Complexity: O(n)
package main

func totalNQueens(n int) int {
	columns := make([]bool, n)
	diagonals := make([]bool, n*2)
	antiDiagonals := make([]bool, n*2)
	answer := 0

	var backtrack func(int)
	backtrack = func(row int) {
		if row == n {
			answer++
			return
		}

		for col := 0; col < n; col++ {
			diagonalIndex := row + col
			antiDiagonalIndex := row - col + n
			if columns[col] || diagonals[diagonalIndex] || antiDiagonals[antiDiagonalIndex] {
				continue
			}

			columns[col] = true
			diagonals[diagonalIndex] = true
			antiDiagonals[antiDiagonalIndex] = true
			backtrack(row + 1)
			columns[col] = false
			diagonals[diagonalIndex] = false
			antiDiagonals[antiDiagonalIndex] = false
		}
	}

	backtrack(0)
	return answer
}
