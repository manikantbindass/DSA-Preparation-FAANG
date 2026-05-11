// LeetCode 2553 - Separate the Digits in an Array
// Time Complexity: O(n * d) | Space Complexity: O(n * d)
package main

func separateDigits(nums []int) []int {
	answer := make([]int, 0)

	for _, value := range nums {
		current := make([]int, 0)
		for value > 0 {
			current = append(current, value%10)
			value /= 10
		}
		for left, right := 0, len(current)-1; left < right; left, right = left+1, right-1 {
			current[left], current[right] = current[right], current[left]
		}
		answer = append(answer, current...)
	}

	return answer
}
