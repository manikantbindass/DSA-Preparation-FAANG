// LeetCode 121 - Best Time to Buy and Sell Stock
// Time Complexity: O(n) | Space Complexity: O(1)
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int answer = 0;
        int minimumPrice = prices[0];

        for (int price : prices) {
            answer = Math.max(answer, price - minimumPrice);
            minimumPrice = Math.min(minimumPrice, price);
        }

        return answer;
    }
}
