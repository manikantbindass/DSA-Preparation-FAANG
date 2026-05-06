# LeetCode 121 - Best Time to Buy and Sell Stock
# Time Complexity: O(n) | Space Complexity: O(1)
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        answer = 0
        minimum_price = prices[0]

        for price in prices:
            answer = max(answer, price - minimum_price)
            minimum_price = min(minimum_price, price)

        return answer
