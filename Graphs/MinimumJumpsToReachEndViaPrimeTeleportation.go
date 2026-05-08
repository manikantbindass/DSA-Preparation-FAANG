// LeetCode 3629 - Minimum Jumps to Reach End via Prime Teleportation
// Time Complexity: O(n log log M + n * d) | Space Complexity: O(M + n)
package main

const factorLimit = 1_000_001

var primeFactors = [factorLimit][]int{}

func init() {
	for factor := 2; factor < factorLimit; factor++ {
		if len(primeFactors[factor]) == 0 {
			for multiple := factor; multiple < factorLimit; multiple += factor {
				primeFactors[multiple] = append(primeFactors[multiple], factor)
			}
		}
	}
}

func minJumps(nums []int) int {
	n := len(nums)
	groups := map[int][]int{}

	for index, value := range nums {
		for _, factor := range primeFactors[value] {
			groups[factor] = append(groups[factor], index)
		}
	}

	visited := make([]bool, n)
	visited[0] = true
	queue := []int{0}
	jumps := 0

	for len(queue) > 0 {
		nextQueue := make([]int, 0)
		for _, index := range queue {
			if index == n-1 {
				return jumps
			}

			if index+1 < n && !visited[index+1] {
				visited[index+1] = true
				nextQueue = append(nextQueue, index+1)
			}
			if index-1 >= 0 && !visited[index-1] {
				visited[index-1] = true
				nextQueue = append(nextQueue, index-1)
			}

			nextIndices, ok := groups[nums[index]]
			if !ok {
				continue
			}

			for _, nextIndex := range nextIndices {
				if !visited[nextIndex] {
					visited[nextIndex] = true
					nextQueue = append(nextQueue, nextIndex)
				}
			}
			groups[nums[index]] = groups[nums[index]][:0]
		}
		queue = nextQueue
		jumps++
	}

	return -1
}
