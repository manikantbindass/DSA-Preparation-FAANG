# LeetCode 796 - Rotate String
# Time Complexity: O(n^2) | Space Complexity: O(n)
class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        return len(s) == len(goal) and goal in (s + s)
