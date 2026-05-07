// LeetCode 3660 - Jump Game IX
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func maxValue(nums []int) []int {
	n := len(nums)
	answer := make([]int, n)
	prefixMax := make([]int, n)
	prefixMax[0] = nums[0]

	for index := 1; index < n; index++ {
		if prefixMax[index-1] > nums[index] {
			prefixMax[index] = prefixMax[index-1]
		} else {
			prefixMax[index] = nums[index]
		}
	}

	suffixMin := 1<<31 - 1
	for index := n - 1; index >= 0; index-- {
		if prefixMax[index] > suffixMin && index+1 < n {
			answer[index] = answer[index+1]
		} else {
			answer[index] = prefixMax[index]
		}
		if nums[index] < suffixMin {
			suffixMin = nums[index]
		}
	}

	return answer
}
