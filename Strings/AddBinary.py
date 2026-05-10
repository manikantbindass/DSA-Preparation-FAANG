# LeetCode 67 - Add Binary
# Time Complexity: O(max(m, n)) | Space Complexity: O(max(m, n))


class Solution:
    def addBinary(self, a: str, b: str) -> str:
        answer: list[str] = []
        first = len(a) - 1
        second = len(b) - 1
        carry = 0

        while first >= 0 or second >= 0 or carry > 0:
            if first >= 0:
                carry += ord(a[first]) - ord("0")
            if second >= 0:
                carry += ord(b[second]) - ord("0")

            answer.append(str(carry % 2))
            carry //= 2
            first -= 1
            second -= 1

        return "".join(reversed(answer))
