// LeetCode 30 - Substring with Concatenation of All Words
// Time Complexity: O(k * n) | Space Complexity: O(words.length)
package main

func findSubstring(s string, words []string) []int {
	wordCount := make(map[string]int)
	for _, word := range words {
		wordCount[word]++
	}

	answer := make([]int, 0)
	stringLength := len(s)
	wordTotal := len(words)
	wordLength := len(words[0])

	for offset := 0; offset < wordLength; offset++ {
		left := offset
		right := offset
		windowCount := make(map[string]int)

		for right+wordLength <= stringLength {
			current := s[right : right+wordLength]
			right += wordLength

			if _, ok := wordCount[current]; !ok {
				windowCount = make(map[string]int)
				left = right
				continue
			}

			windowCount[current]++

			for windowCount[current] > wordCount[current] {
				removed := s[left : left+wordLength]
				windowCount[removed]--
				if windowCount[removed] == 0 {
					delete(windowCount, removed)
				}
				left += wordLength
			}

			if right-left == wordTotal*wordLength {
				answer = append(answer, left)
			}
		}
	}

	return answer
}
