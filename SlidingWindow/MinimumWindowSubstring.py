# LeetCode 76 - Minimum Window Substring
# Time Complexity: O(s + t) | Space Complexity: O(1)


class Solution:
    def minWindow(self, s: str, t: str) -> str:
        need = [0] * 128
        window = [0] * 128
        for char in t:
            need[ord(char)] += 1

        best_start = -1
        best_length = len(s) + 1
        matched = 0
        left = 0

        for right, char in enumerate(s):
            added = ord(char)
            window[added] += 1
            if window[added] <= need[added]:
                matched += 1

            while matched == len(t):
                if right - left + 1 < best_length:
                    best_length = right - left + 1
                    best_start = left

                removed = ord(s[left])
                if window[removed] <= need[removed]:
                    matched -= 1
                window[removed] -= 1
                left += 1

        return "" if best_start < 0 else s[best_start:best_start + best_length]
