# LeetCode Problem 3360: Stone Removal Game
# Difficulty: Easy
# Link: https://leetcode.com/problems/stone-removal-game/
# Approach: Simulate the decreasing removal sizes and check the parity of legal moves.
# Time Complexity: O(1)
# Space Complexity: O(1)


class Solution:
    def canAliceWin(self, n: int) -> bool:
        alice_turn = True
        for stones in range(10, 0, -1):
            if n < stones:
                break
            n -= stones
            alice_turn = not alice_turn
        return not alice_turn
