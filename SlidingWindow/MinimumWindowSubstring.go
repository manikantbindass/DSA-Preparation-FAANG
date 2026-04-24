// LeetCode 76 - Minimum Window Substring
// Time Complexity: O(s + t) | Space Complexity: O(1)
package main

func minWindow(s string, t string) string {
	need := make([]int, 128)
	window := make([]int, 128)
	for i := 0; i < len(t); i++ {
		need[t[i]]++
	}

	bestStart := -1
	bestLength := len(s) + 1
	matched := 0
	left := 0

	for right := 0; right < len(s); right++ {
		added := s[right]
		window[added]++
		if window[added] <= need[added] {
			matched++
		}

		for matched == len(t) {
			if right-left+1 < bestLength {
				bestLength = right - left + 1
				bestStart = left
			}

			removed := s[left]
			if window[removed] <= need[removed] {
				matched--
			}
			window[removed]--
			left++
		}
	}

	if bestStart < 0 {
		return ""
	}
	return s[bestStart : bestStart+bestLength]
}
