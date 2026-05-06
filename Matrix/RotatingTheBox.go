// LeetCode 1861 - Rotating the Box
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
package main

func rotateTheBox(boxGrid [][]byte) [][]byte {
	rows := len(boxGrid)
	cols := len(boxGrid[0])
	rotated := make([][]byte, cols)

	for row := 0; row < cols; row++ {
		rotated[row] = make([]byte, rows)
	}

	for row := 0; row < rows; row++ {
		for col := 0; col < cols; col++ {
			rotated[col][rows-row-1] = boxGrid[row][col]
		}
	}

	for col := 0; col < rows; col++ {
		emptyRows := make([]int, 0)

		for row := cols - 1; row >= 0; row-- {
			if rotated[row][col] == '*' {
				emptyRows = emptyRows[:0]
			} else if rotated[row][col] == '.' {
				emptyRows = append(emptyRows, row)
			} else if len(emptyRows) > 0 {
				rotated[emptyRows[0]][col] = '#'
				rotated[row][col] = '.'
				emptyRows = append(emptyRows[1:], row)
			}
		}
	}

	return rotated
}
