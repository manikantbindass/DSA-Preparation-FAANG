// LeetCode Problem 3331: Find Subtree Sizes After Changes
// Difficulty: Medium
// Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/
// Approach: Traverse the original tree iteratively and accumulate each subtree at its new parent on exit.
// Time Complexity: O(n)
// Space Complexity: O(n)

package findsubtreesizesafterchanges

func findSubtreeSizes(parent []int, s string) []int {
	n := len(parent)
	children := make([][]int, n)
	for node := 1; node < n; node++ {
		children[parent[node]] = append(children[parent[node]], node)
	}

	var nearest [26]int
	for i := range nearest {
		nearest[i] = -1
	}
	previous := make([]int, n)
	size := make([]int, n)
	stack := []int{0}
	for len(stack) > 0 {
		event := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if event >= 0 {
			node := event
			letter := s[node] - 'a'
			size[node] = 1
			previous[node] = nearest[letter]
			nearest[letter] = node
			// The complemented node marks the exit after all original children.
			stack = append(stack, ^node)
			stack = append(stack, children[node]...)
		} else {
			node := ^event
			letter := s[node] - 'a'
			ancestor := previous[node]
			nearest[letter] = ancestor
			if ancestor == -1 {
				ancestor = parent[node]
			}
			if ancestor != -1 {
				size[ancestor] += size[node]
			}
		}
	}
	return size
}
