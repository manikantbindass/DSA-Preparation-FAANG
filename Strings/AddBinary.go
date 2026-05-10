// LeetCode 67 - Add Binary
// Time Complexity: O(max(m, n)) | Space Complexity: O(max(m, n))
package main

func addBinary(a string, b string) string {
	answer := make([]byte, 0, max(len(a), len(b))+1)
	first := len(a) - 1
	second := len(b) - 1
	carry := 0

	for first >= 0 || second >= 0 || carry > 0 {
		if first >= 0 {
			carry += int(a[first] - '0')
		}
		if second >= 0 {
			carry += int(b[second] - '0')
		}

		answer = append(answer, byte('0'+carry%2))
		carry /= 2
		first--
		second--
	}

	for left, right := 0, len(answer)-1; left < right; left, right = left+1, right-1 {
		answer[left], answer[right] = answer[right], answer[left]
	}

	return string(answer)
}
