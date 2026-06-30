# ──────────────────────────────────────────────────────────────────────
# LeetCode #192 · Word Frequency
# Difficulty : Medium
# Topics     : Shell
# URL        : https://leetcode.com/problems/word-frequency/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The approach reads the file, splits it into words (by whitespace),
#   counts occurrences using a hash map, then sorts by frequency
#   descending and prints the results. In Java, we use BufferedReader to
#   read lines, split on whitespace, and a HashMap for counting. In
#   Python, we use collections.Counter and split. In Go, we use
#   bufio.Scanner with default split (words) and a map for counting, then
#   sort a slice of key-value pairs.
# 
# Complexity
#   Time  : O(n log n) due to sorting, where n is the number of unique words
#   Space : O(n) for storing word counts
# 
# Runtime  : 76 ms
# Memory   : 3.8 MB
# 
# Examples
#   Example 1:
#     Input  : a
#     Output : 
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import sys
from collections import Counter

def main():
    with open('words.txt', 'r') as f:
        words = f.read().split()
    freq = Counter(words)
    for word, count in freq.most_common():
        print(f"{word} {count}")

if __name__ == "__main__":
    main()
