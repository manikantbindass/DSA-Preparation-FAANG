// LeetCode 60 - Permutation Sequence
// Time Complexity: O(n^2) | Space Complexity: O(n)
package main

import "strings"

func getPermutation(n int, k int) string {
	var answer strings.Builder
	visited := make([]bool, n+1)

	for index := 0; index < n; index++ {
		factorial := 1
		for value := 1; value < n-index; value++ {
			factorial *= value
		}

		for value := 1; value <= n; value++ {
			if !visited[value] {
				if k > factorial {
					k -= factorial
				} else {
					answer.WriteByte(byte('0' + value))
					visited[value] = true
					break
				}
			}
		}
	}

	return answer.String()
}
