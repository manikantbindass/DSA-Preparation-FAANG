// LeetCode 22 - Generate Parentheses
// Time Complexity: O(4^n / sqrt(n)) | Space Complexity: O(n)
package backtracking

func generateParenthesis(n int) []string {
	ans := []string{}

	var dfs func(int, int, []byte)
	dfs = func(open int, close int, path []byte) {
		if open == n && close == n {
			ans = append(ans, string(path))
			return
		}

		if open < n {
			dfs(open+1, close, append(path, '('))
		}

		if close < open {
			dfs(open, close+1, append(path, ')'))
		}
	}

	dfs(0, 0, []byte{})
	return ans
}
