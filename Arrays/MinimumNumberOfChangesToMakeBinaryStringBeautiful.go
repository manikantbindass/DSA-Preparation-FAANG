// LeetCode Problem 2914: Minimum Number of Changes to Make Binary String Beautiful
// Difficulty: Medium
// Link: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
// Approach: Count mismatched adjacent pairs starting at even indices.
// Time Complexity: O(n)
// Space Complexity: O(1)

package minimumnumberofchangestomakebinarystringbeautiful

func minChanges(s string) int {
	changes := 0
	for i := 0; i < len(s); i += 2 {
		if s[i] != s[i+1] {
			changes++
		}
	}
	return changes
}
