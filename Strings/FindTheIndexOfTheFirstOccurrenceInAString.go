// LeetCode 28 - Find the Index of the First Occurrence in a String
// Time Complexity: O(n * m) | Space Complexity: O(1)
package main

func strStr(haystack string, needle string) int {
	if needle == "" {
		return 0
	}

	haystackLength := len(haystack)
	needleLength := len(needle)
	haystackIndex := 0
	needleIndex := 0

	for haystackIndex < haystackLength {
		if haystack[haystackIndex] == needle[needleIndex] {
			if needleLength == 1 {
				return haystackIndex
			}
			haystackIndex++
			needleIndex++
		} else {
			haystackIndex -= needleIndex - 1
			needleIndex = 0
		}

		if needleIndex == needleLength {
			return haystackIndex - needleIndex
		}
	}

	return -1
}
