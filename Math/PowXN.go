// LeetCode 50 - Pow(x, n)
// Time Complexity: O(log n) | Space Complexity: O(1)
package main

func myPow(x float64, n int) float64 {
	if n >= 0 {
		return fastPow(x, int64(n))
	}
	return 1.0 / fastPow(x, -int64(n))
}

func fastPow(base float64, exponent int64) float64 {
	answer := 1.0

	for exponent > 0 {
		if exponent&1 == 1 {
			answer *= base
		}
		base *= base
		exponent >>= 1
	}

	return answer
}
