// LeetCode 78 - Subsets
// Time Complexity: O(n * 2^n) | Space Complexity: O(n)
package backtracking

func subsets(nums []int) (ans [][]int) {
	current := []int{}

	var dfs func(int)
	dfs = func(index int) {
		if index == len(nums) {
			ans = append(ans, append([]int(nil), current...))
			return
		}

		dfs(index + 1)
		current = append(current, nums[index])
		dfs(index + 1)
		current = current[:len(current)-1]
	}

	dfs(0)
	return
}
