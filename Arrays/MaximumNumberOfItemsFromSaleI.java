/*
 * LeetCode Problem 3946: Maximum Number of Items From Sale I
 * Problem Number: 3946
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
 *
 * You are given a 2D integer array items, where items[i] = [factori, pricei]
 * represents the ith item. You are also given an integer budget.
 *
 * There are unlimited copies of each item available for purchase.You may buy any
 * number of copies of any items such that the total cost of the purchased copies
 * is at most budget.
 *
 * After buying items, you may receive free copies according to the following
 * rules:
 *
 * 	For each item i that you bought at least one copy of, you receive one free copy
 * of every item j such that j != i and factori divides factorj.
 * 	Buying multiple copies of the same item i does not give additional free copies
 * through item i.
 * 	The same item j can be received multiple times for free if it is received from
 * purchases of different item types.
 *
 * Return the maximum total number of item copies you can obtain, including both
 * purchased copies and free copies, while spending at most budget on purchased
 * items.
 *
 *
 *
 * Example 1:
 *
 * Input: items = [[6,2],[2,6],[3,4]], budget = 9
 *
 * Output: 4
 *
 * Explanation:
 *
 * 	You can buy 2 copies of item 0 and 1 copy of item 2 for a total cost of 2 * 2 +
 * 4 = 8, which is not greater than budget = 9.
 * 	Buying item 2 gives 1 free copy of item 0, because factor2 = 3 divides factor0
 * = 6.
 * 	You leave with 3 purchased copies and 1 free copy, for a total of 4 item
 * copies.
 *
 * Example 2:
 *
 * Input: items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8
 *
 * Output: 10
 *
 * Explanation:
 *
 * 	You can buy 1 copy of item 0, 1 copy of item 1, and 2 copies of item 2 for a
 * total cost of 4 + 2 + 2 * 1 = 8.
 * 	Buying item 0 gives 1 free copy of items 2, 3, and 4.
 * 	Buying item 1 gives 1 free copy of items 3 and 4.
 * 	Buying item 2 gives 1 free copy of item 4.
 * 	Thus, you receive 6 free copies. You leave with 4 purchased copies and 6 free
 * copies, for a total of 10 item copies.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= items.length <= 1000
 * 	items[i] = [factori, pricei]
 * 	1 <= factori, pricei <= 1500
 * 	1 <= budget <= 1500
 *
 * Example 1:
 * Input: items = [[6,2],[2,6],[3,4]], budget = 9
 * Output: 4
 *
 * Example 2:
 * Input: items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8
 * Output: 10
 *
 * Constraints:
 * - 1 <= items.length <= 1000
 * - items[i] = [factori, pricei]
 * - 1 <= factori, pricei <= 1500
 * - 1 <= budget <= 1500
 *
 * Topics: Array, Dynamic Programming, Greedy
 * Time Complexity: O(F log F + n*budget), where F is the maximum factor
 * Space Complexity: O(F + budget)
 * Approach: Count multiples by factor, then use 0/1 knapsack for first purchases and the cheapest item for extra copies.
 */

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int cheapest = items[0][1];
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            cheapest = Math.min(cheapest, item[1]);
        }

        int[] frequency = new int[maxFactor + 1];
        for (int[] item : items) {
            frequency[item[0]]++;
        }
        int[] reward = new int[maxFactor + 1];
        for (int factor = 1; factor <= maxFactor; factor++) {
            if (frequency[factor] == 0) {
                continue;
            }
            for (int multiple = factor; multiple <= maxFactor; multiple += factor) {
                reward[factor] += frequency[multiple];
            }
        }

        // Any budget left after first purchases can buy extra copies at this price.
        int[] dp = new int[budget + 1];
        for (int spend = 0; spend <= budget; spend++) {
            dp[spend] = spend / cheapest;
        }
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            for (int spend = budget; spend >= price; spend--) {
                dp[spend] = Math.max(dp[spend], dp[spend - price] + reward[factor]);
            }
        }
        return dp[budget];
    }
}
