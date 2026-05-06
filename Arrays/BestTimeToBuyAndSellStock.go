// LeetCode 121 - Best Time to Buy and Sell Stock
// Time Complexity: O(n) | Space Complexity: O(1)
package main

func maxProfit(prices []int) int {
	answer := 0
	minimumPrice := prices[0]

	for _, price := range prices {
		if price-minimumPrice > answer {
			answer = price - minimumPrice
		}
		if price < minimumPrice {
			minimumPrice = price
		}
	}

	return answer
}
