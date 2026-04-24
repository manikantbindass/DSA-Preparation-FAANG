// LeetCode 71 - Simplify Path
// Time Complexity: O(n) | Space Complexity: O(n)
package main

import "strings"

func simplifyPath(path string) string {
	stack := make([]string, 0)

	for _, part := range strings.Split(path, "/") {
		if part == "" || part == "." {
			continue
		}
		if part == ".." {
			if len(stack) > 0 {
				stack = stack[:len(stack)-1]
			}
		} else {
			stack = append(stack, part)
		}
	}

	return "/" + strings.Join(stack, "/")
}
