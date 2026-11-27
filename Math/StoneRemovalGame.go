// LeetCode Problem 3360: Stone Removal Game
// Difficulty: Easy
// Link: https://leetcode.com/problems/stone-removal-game/
// Approach: Simulate the decreasing removal sizes and check the parity of legal moves.
// Time Complexity: O(1)
// Space Complexity: O(1)

package stoneremovalgame

func canAliceWin(n int) bool {
	aliceTurn := true
	for stones := 10; stones > 0 && n >= stones; stones-- {
		n -= stones
		aliceTurn = !aliceTurn
	}
	return !aliceTurn
}
