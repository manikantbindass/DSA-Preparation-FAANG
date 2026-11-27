// LeetCode Problem 2124: Check if All A's Appears Before All B's
// Difficulty: Easy
// Link: https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
// Approach: Check whether an adjacent b-to-a transition occurs.
// Time Complexity: O(n)
// Space Complexity: O(1)

package checkifallasappearsbeforeallbs

import "strings"

func checkString(s string) bool {
	return !strings.Contains(s, "ba")
}
