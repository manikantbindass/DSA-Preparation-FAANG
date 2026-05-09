# LeetCode 65 - Valid Number
# Time Complexity: O(n) | Space Complexity: O(1)


class Solution:
    def isNumber(self, s: str) -> bool:
        n = len(s)
        index = 0

        if s[index] in "+-":
            index += 1

        if index == n:
            return False

        if s[index] == "." and (index + 1 == n or s[index + 1] in "eE"):
            return False

        dots = 0
        exponents = 0
        current = index

        while current < n:
            ch = s[current]

            if ch == ".":
                if exponents > 0 or dots > 0:
                    return False
                dots += 1
            elif ch in "eE":
                if exponents > 0 or current == index or current == n - 1:
                    return False
                exponents += 1
                if s[current + 1] in "+-":
                    current += 1
                    if current == n - 1:
                        return False
            elif ch < "0" or ch > "9":
                return False

            current += 1

        return True
