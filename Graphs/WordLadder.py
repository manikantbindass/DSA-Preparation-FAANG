# LeetCode 127 - Word Ladder
# Time Complexity: O(n * m * 26) | Space Complexity: O(n)
from collections import deque
from typing import List


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        words = set(wordList)
        if endWord not in words:
            return 0

        queue = deque([beginWord])
        words.discard(beginWord)
        length = 1

        while queue:
            for _ in range(len(queue)):
                word = queue.popleft()
                if word == endWord:
                    return length

                chars = list(word)
                for i, original in enumerate(chars):
                    for code in range(ord("a"), ord("z") + 1):
                        candidate = chr(code)
                        if candidate == original:
                            continue

                        chars[i] = candidate
                        next_word = "".join(chars)
                        if next_word in words:
                            words.remove(next_word)
                            queue.append(next_word)

                    chars[i] = original

            length += 1

        return 0
