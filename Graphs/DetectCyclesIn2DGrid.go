// LeetCode 1559 - Detect Cycles in 2D Grid
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
package graphs

func containsCycle(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	visited := make([][]bool, m)
	for i := range visited {
		visited[i] = make([]bool, n)
	}

	directions := []int{-1, 0, 1, 0, -1}
	var dfs func(int, int, int, int) bool
	dfs = func(row int, col int, parentRow int, parentCol int) bool {
		visited[row][col] = true

		for index := 0; index < 4; index++ {
			nextRow := row + directions[index]
			nextCol := col + directions[index+1]

			if nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n {
				continue
			}

			if grid[nextRow][nextCol] != grid[row][col] || (nextRow == parentRow && nextCol == parentCol) {
				continue
			}

			if visited[nextRow][nextCol] || dfs(nextRow, nextCol, row, col) {
				return true
			}
		}

		return false
	}

	for row := 0; row < m; row++ {
		for col := 0; col < n; col++ {
			if !visited[row][col] && dfs(row, col, -1, -1) {
				return true
			}
		}
	}

	return false
}
