"""
LeetCode Problem 122: Best Time to Buy and Sell Stock II
Problem Number: 122
Difficulty: Medium
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

You are given an integer array prices where prices[i] is the price of a given stock
on the i-th day. On each day, you may decide to buy and/or sell the stock.
You can only hold at most one share of the stock at any time.

Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price=1) and sell on day 3 (price=5), profit=4.
Then buy on day 4 (price=3) and sell on day 5 (price=6), profit=3.
Total profit = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price=1) and sell on day 5 (price=5), profit=4.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: No transaction is done, max profit = 0.

Constraints:
- 1 <= prices.length <= 3 * 10^4
- 0 <= prices[i] <= 10^4

Topics: Array, Dynamic Programming, Greedy
Time Complexity: O(n) - single pass through the array
Space Complexity: O(1) - only using constant extra space
"""

from typing import List

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_profit = 0
        
        # Sum up all positive price differences
        for i in range(1, len(prices)):
            if prices[i] > prices[i - 1]:
                max_profit += prices[i] - prices[i - 1]
        
        return max_profit
