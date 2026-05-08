# LeetCode 50 - Pow(x, n)
# Time Complexity: O(log n) | Space Complexity: O(1)


class Solution:
    def myPow(self, x: float, n: int) -> float:
        return self._fast_pow(x, n) if n >= 0 else 1.0 / self._fast_pow(x, -n)

    def _fast_pow(self, base: float, exponent: int) -> float:
        answer = 1.0

        while exponent > 0:
            if exponent & 1:
                answer *= base
            base *= base
            exponent >>= 1

        return answer
