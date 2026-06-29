# ──────────────────────────────────────────────────────────────────────
# LeetCode #2099 · Number of Strings That Appear as Substrings in Word
# Difficulty : Easy
# Topics     : Array, String
# URL        : https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem asks to count how many strings in the patterns array
#   appear as a substring in the given word. The simplest approach is to
#   iterate through each pattern and check if it is a substring of word
#   using the built-in substring search (e.g., contains in Java, in
#   operator in Python, strings.Contains in Go). Since the constraints are
#   small (max length 100), this direct approach is efficient and clean.
# 
# Complexity
#   Time  : O(n * m) where n is number of patterns and m is average pattern length, but due to small constraints it's fine
#   Space : O(1)
# 
# Runtime  : 0 ms
# Memory   : 42.8 MB
# 
# Examples
#   Example 1:
#     Input  : patterns = ["a","abc","bc","d"], word = "abc"
#     Output : 3
#   Example 2:
#     Input  : patterns = ["a","b","c"], word = "aaaaabbbbb"
#     Output : 2
#   Example 3:
#     Input  : patterns = ["a","a","a"], word = "ab"
#     Output : 3
#     Explanation: Each of the patterns appears as a substring in word "ab".
# 
# Constraints
#   · 1 <= patterns.length <= 100
#   · 1 <= patterns[i].length <= 100
#   · 1 <= word.length <= 100
#   · patterns[i] and word consist of lowercase English letters.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def numOfStrings(self, patterns: List[str], word: str) -> int:
        count = 0
        for p in patterns:
            if p in word:
                count += 1
        return count
