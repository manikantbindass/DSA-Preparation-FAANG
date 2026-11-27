# LeetCode Problem 327: Count of Range Sum
# Difficulty: Hard
# Link: https://leetcode.com/problems/count-of-range-sum/
# Approach: Use a Fenwick tree over prefix sums to count earlier prefixes in the inclusive target interval.
# Time Complexity: O(n log n)
# Space Complexity: O(n)


from bisect import bisect_left, bisect_right
from itertools import accumulate


class Solution:
    def countRangeSum(self, nums: list[int], lower: int, upper: int) -> int:
        prefixes = list(accumulate(nums, initial=0))
        ordered = sorted(set(prefixes))
        tree = [0] * (len(ordered) + 1)

        def count_first(size: int) -> int:
            count = 0
            while size > 0:
                count += tree[size]
                size -= size & -size
            return count

        answer = 0
        for prefix in prefixes:
            left = bisect_left(ordered, prefix - upper)
            right = bisect_right(ordered, prefix - lower)
            answer += count_first(right) - count_first(left)

            index = bisect_left(ordered, prefix) + 1
            while index < len(tree):
                tree[index] += 1
                index += index & -index
        return answer
