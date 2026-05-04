// LeetCode 796 - Rotate String
// Time Complexity: O(n^2) | Space Complexity: O(n)
package main

import "strings"

func rotateString(s string, goal string) bool {
	return len(s) == len(goal) && strings.Contains(s+s, goal)
}
