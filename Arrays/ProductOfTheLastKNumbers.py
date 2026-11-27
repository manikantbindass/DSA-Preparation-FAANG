# LeetCode Problem 1352: Product of the Last K Numbers
# Difficulty: Medium
# Link: https://leetcode.com/problems/product-of-the-last-k-numbers/
# Approach: Track prefix products since the last zero and divide to get a suffix product.
# Time Complexity: O(1) amortized per add, O(1) per query
# Space Complexity: O(n)


class ProductOfNumbers:
    def __init__(self):
        self.prefixes = [1]

    def add(self, num: int) -> None:
        if num == 0:
            self.prefixes = [1]
        else:
            self.prefixes.append(self.prefixes[-1] * num)

    def getProduct(self, k: int) -> int:
        if k >= len(self.prefixes):
            return 0
        return self.prefixes[-1] // self.prefixes[-1 - k]
