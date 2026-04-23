// LeetCode 2121 - Intervals Between Identical Elements
// Time Complexity: O(n) | Space Complexity: O(n)
package main

func getDistances(arr []int) []int64 {
	n := len(arr)
	ans := make([]int64, n)
	groups := make(map[int][]int)

	for i, value := range arr {
		groups[value] = append(groups[value], i)
	}

	for _, indices := range groups {
		m := len(indices)
		left := int64(0)
		right := int64(-m * indices[0])

		for _, index := range indices {
			right += int64(index)
		}

		for i, index := range indices {
			ans[index] = left + right

			if i+1 < m {
				gap := int64(indices[i+1] - index)
				left += gap * int64(i+1)
				right -= gap * int64(m-i-1)
			}
		}
	}

	return ans
}
