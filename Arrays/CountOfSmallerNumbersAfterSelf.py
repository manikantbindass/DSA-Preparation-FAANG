# LeetCode Problem 315: Count of Smaller Numbers After Self
# Difficulty: Hard
# Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
# Approach: Scan from right to left and use a Fenwick tree over compressed values to count strictly smaller elements.
# Time Complexity: O(n log n)
# Space Complexity: O(n)


class Solution:
    def countSmaller(self, nums: list[int]) -> list[int]:
        ranks = {value: i for i, value in enumerate(sorted(set(nums)), start=1)}
        tree = [0] * (len(ranks) + 1)
        answer = [0] * len(nums)

        for i in range(len(nums) - 1, -1, -1):
            rank = ranks[nums[i]]
            index = rank - 1
            while index > 0:
                answer[i] += tree[index]
                index -= index & -index

            index = rank
            while index < len(tree):
                tree[index] += 1
                index += index & -index
        return answer
