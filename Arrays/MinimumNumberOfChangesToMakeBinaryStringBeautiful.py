# LeetCode Problem 2914: Minimum Number of Changes to Make Binary String Beautiful
# Difficulty: Medium
# Link: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
# Approach: Count mismatched adjacent pairs starting at even indices.
# Time Complexity: O(n)
# Space Complexity: O(1)


class Solution:
    def minChanges(self, s: str) -> int:
        return sum(s[i] != s[i + 1] for i in range(0, len(s), 2))
