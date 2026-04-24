# LeetCode 2833 - Furthest Point From Origin
# Time Complexity: O(n) | Space Complexity: O(1)


class Solution:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        return abs(moves.count("L") - moves.count("R")) + moves.count("_")
