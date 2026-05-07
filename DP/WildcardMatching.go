// LeetCode 44 - Wildcard Matching
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
package main

func isMatch(s string, p string) bool {
	textLength := len(s)
	patternLength := len(p)
	memo := make([][]int8, textLength)

	for index := 0; index < textLength; index++ {
		memo[index] = make([]int8, patternLength)
		for patternIndex := 0; patternIndex < patternLength; patternIndex++ {
			memo[index][patternIndex] = -1
		}
	}

	var dfs func(int, int) bool
	dfs = func(textIndex int, patternIndex int) bool {
		if textIndex >= textLength {
			return patternIndex >= patternLength || (p[patternIndex] == '*' && dfs(textIndex, patternIndex+1))
		}

		if patternIndex >= patternLength {
			return false
		}

		if memo[textIndex][patternIndex] != -1 {
			return memo[textIndex][patternIndex] == 1
		}

		var matched bool
		if p[patternIndex] == '*' {
			matched = dfs(textIndex+1, patternIndex) ||
				dfs(textIndex+1, patternIndex+1) ||
				dfs(textIndex, patternIndex+1)
		} else {
			matched = (p[patternIndex] == '?' || s[textIndex] == p[patternIndex]) &&
				dfs(textIndex+1, patternIndex+1)
		}

		if matched {
			memo[textIndex][patternIndex] = 1
		} else {
			memo[textIndex][patternIndex] = 0
		}
		return matched
	}

	return dfs(0, 0)
}
