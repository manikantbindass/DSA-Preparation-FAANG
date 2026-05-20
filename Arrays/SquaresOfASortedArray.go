// LeetCode 977 - Squares of a Sorted Array
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func sortedSquares(nums []int) []int {
	length := len(nums)
	answer := make([]int, length)
	left := 0
	right := length - 1

	for index := length - 1; index >= 0; index-- {
		leftSquare := nums[left] * nums[left]
		rightSquare := nums[right] * nums[right]

		if leftSquare > rightSquare {
			answer[index] = leftSquare
			left++
		} else {
			answer[index] = rightSquare
			right--
		}
	}

	return answer
}
