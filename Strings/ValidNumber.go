// LeetCode 65 - Valid Number
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func isNumber(s string) bool {
	n := len(s)
	index := 0

	if s[index] == '+' || s[index] == '-' {
		index++
	}

	if index == n {
		return false
	}

	if s[index] == '.' && (index+1 == n || s[index+1] == 'e' || s[index+1] == 'E') {
		return false
	}

	dots := 0
	exponents := 0

	for current := index; current < n; current++ {
		ch := s[current]

		if ch == '.' {
			if exponents > 0 || dots > 0 {
				return false
			}
			dots++
		} else if ch == 'e' || ch == 'E' {
			if exponents > 0 || current == index || current == n-1 {
				return false
			}
			exponents++
			if s[current+1] == '+' || s[current+1] == '-' {
				current++
				if current == n-1 {
					return false
				}
			}
		} else if ch < '0' || ch > '9' {
			return false
		}
	}

	return true
}
