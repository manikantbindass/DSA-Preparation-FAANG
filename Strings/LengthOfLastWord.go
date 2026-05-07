// LeetCode 58 - Length of Last Word
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func lengthOfLastWord(s string) int {
	end := len(s) - 1

	for end >= 0 && s[end] == ' ' {
		end--
	}

	start := end
	for start >= 0 && s[start] != ' ' {
		start--
	}

	return end - start
}
