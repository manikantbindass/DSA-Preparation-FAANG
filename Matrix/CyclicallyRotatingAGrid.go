// LeetCode 1914 - Cyclically Rotating a Grid
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
package main

func rotateGrid(grid [][]int, k int) [][]int {
	rows := len(grid)
	cols := len(grid[0])

	var rotateLayer func(int, int)
	rotateLayer = func(layer int, shift int) {
		values := make([]int, 0)

		for col := layer; col < cols-layer-1; col++ {
			values = append(values, grid[layer][col])
		}
		for row := layer; row < rows-layer-1; row++ {
			values = append(values, grid[row][cols-layer-1])
		}
		for col := cols - layer - 1; col > layer; col-- {
			values = append(values, grid[rows-layer-1][col])
		}
		for row := rows - layer - 1; row > layer; row-- {
			values = append(values, grid[row][layer])
		}

		length := len(values)
		shift %= length
		if shift == 0 {
			return
		}

		for col := layer; col < cols-layer-1; col++ {
			grid[layer][col] = values[shift%length]
			shift++
		}
		for row := layer; row < rows-layer-1; row++ {
			grid[row][cols-layer-1] = values[shift%length]
			shift++
		}
		for col := cols - layer - 1; col > layer; col-- {
			grid[rows-layer-1][col] = values[shift%length]
			shift++
		}
		for row := rows - layer - 1; row > layer; row-- {
			grid[row][layer] = values[shift%length]
			shift++
		}
	}

	for layer := 0; layer < min(rows, cols)/2; layer++ {
		rotateLayer(layer, k)
	}

	return grid
}
