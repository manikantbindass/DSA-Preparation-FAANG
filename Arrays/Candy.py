"""
LeetCode Problem 135: Candy
Problem Number: 135
Difficulty: Hard
Link: https://leetcode.com/problems/candy/

There are n children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:
- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.

Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.

Constraints:
- n == ratings.length
- 1 <= n <= 2 * 10^4
- 0 <= ratings[i] <= 2 * 10^4

Topics: Array, Greedy
Time Complexity: O(n) - two passes through the array
Space Complexity: O(n) - for the left and right arrays
"""

from typing import List

class Solution:
    def candy(self, ratings: List[int]) -> int:
        n = len(ratings)
        left_to_right = [1] * n
        right_to_left = [1] * n
        
        # Left to right pass: ensure right child gets more if rating is higher
        for i in range(1, n):
            if ratings[i] > ratings[i - 1]:
                left_to_right[i] = left_to_right[i - 1] + 1
        
        # Right to left pass: ensure left child gets more if rating is higher
        for i in range(n - 2, -1, -1):
            if ratings[i] > ratings[i + 1]:
                right_to_left[i] = right_to_left[i + 1] + 1
        
        # Sum the maximum of both passes for each child
        total_candies = 0
        for i in range(n):
            total_candies += max(left_to_right[i], right_to_left[i])
        
        return total_candies
