// LeetCode Problem 2911: Minimum Changes to Make K Semi-palindromes
// Difficulty: Hard
// Link: https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
// Approach: Precompute the mismatch cost for each substring over its proper divisors, then partition with dynamic programming.
// Time Complexity: O(n^3 log n + k*n^2)
// Space Complexity: O(n^2)

package minimumchangestomakeksemipalindromes

func minimumChanges(s string, k int) int {
	n := len(s)
	divisors := make([][]int, n+1)
	for step := 1; step <= n/2; step++ {
		for length := 2 * step; length <= n; length += step {
			divisors[length] = append(divisors[length], step)
		}
	}

	cost := make([][]int, n)
	for start := range cost {
		cost[start] = make([]int, n+1)
	}
	for length := 2; length <= n; length++ {
		for start := 0; start+length <= n; start++ {
			end := start + length
			best := length
			for _, step := range divisors[length] {
				changes := 0
				for offset := 0; offset < step; offset++ {
					for left, right := start+offset, end-step+offset; left < right; left, right = left+step, right-step {
						if s[left] != s[right] {
							changes++
						}
					}
				}
				best = min(best, changes)
				if best == 0 {
					break
				}
			}
			cost[start][end] = best
		}
	}

	previous, current := make([]int, n+1), make([]int, n+1)
	for end := 1; end <= n; end++ {
		previous[end] = n
	}
	for parts := 1; parts <= k; parts++ {
		firstCut := 2 * (parts - 1)
		for end := 2 * parts; end <= n; end++ {
			current[end] = n
			for cut := firstCut; cut <= end-2; cut++ {
				current[end] = min(current[end], previous[cut]+cost[cut][end])
			}
		}
		previous, current = current, previous
	}
	return previous[n]
}
