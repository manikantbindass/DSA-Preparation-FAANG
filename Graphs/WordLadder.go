// LeetCode 127 - Word Ladder
// Time Complexity: O(n * m * 26) | Space Complexity: O(n)
package graphs

func ladderLength(beginWord string, endWord string, wordList []string) int {
	words := make(map[string]bool, len(wordList))
	for _, word := range wordList {
		words[word] = true
	}

	if !words[endWord] {
		return 0
	}

	queue := []string{beginWord}
	delete(words, beginWord)
	length := 1

	for len(queue) > 0 {
		size := len(queue)

		for i := 0; i < size; i++ {
			word := queue[0]
			queue = queue[1:]

			if word == endWord {
				return length
			}

			chars := []byte(word)
			for j, original := range chars {
				for c := byte('a'); c <= byte('z'); c++ {
					if c == original {
						continue
					}

					chars[j] = c
					next := string(chars)
					if words[next] {
						delete(words, next)
						queue = append(queue, next)
					}
				}

				chars[j] = original
			}
		}

		length++
	}

	return 0
}
