# LeetCode 367 - Valid Perfect Square
# Time Complexity: O(log n) | Space Complexity: O(1)


class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        left = 1
        right = num

        while left <= right:
            mid = left + (right - left) // 2
            square = mid * mid

            if square == num:
                return True
            if square < num:
                left = mid + 1
            else:
                right = mid - 1

        return False
