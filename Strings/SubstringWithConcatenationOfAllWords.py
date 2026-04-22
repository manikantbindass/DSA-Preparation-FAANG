# LeetCode 30 - Substring with Concatenation of All Words
# Time Complexity: O(k * n) | Space Complexity: O(len(words))
from collections import Counter, defaultdict
from typing import List


class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        word_count = Counter(words)
        answer = []
        string_length = len(s)
        word_total = len(words)
        word_length = len(words[0])

        for offset in range(word_length):
            left = offset
            right = offset
            window_count = defaultdict(int)

            while right + word_length <= string_length:
                current = s[right:right + word_length]
                right += word_length

                if current not in word_count:
                    window_count.clear()
                    left = right
                    continue

                window_count[current] += 1

                while window_count[current] > word_count[current]:
                    removed = s[left:left + word_length]
                    window_count[removed] -= 1
                    if window_count[removed] == 0:
                        del window_count[removed]
                    left += word_length

                if right - left == word_total * word_length:
                    answer.append(left)

        return answer
