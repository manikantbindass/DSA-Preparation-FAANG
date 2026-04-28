# LeetCode 28 - Find the Index of the First Occurrence in a String
# Time Complexity: O(n * m) | Space Complexity: O(1)
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        if needle == "":
            return 0

        haystack_length = len(haystack)
        needle_length = len(needle)
        haystack_index = 0
        needle_index = 0

        while haystack_index < haystack_length:
            if haystack[haystack_index] == needle[needle_index]:
                if needle_length == 1:
                    return haystack_index
                haystack_index += 1
                needle_index += 1
            else:
                haystack_index -= needle_index - 1
                needle_index = 0

            if needle_index == needle_length:
                return haystack_index - needle_index

        return -1
