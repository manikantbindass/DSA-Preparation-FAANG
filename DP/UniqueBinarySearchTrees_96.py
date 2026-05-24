# 96. Unique Binary Search Trees
# https://leetcode.com/problems/unique-binary-search-trees/
# Difficulty: Medium

from typing import List

class Solution:
    def numTrees(self, n: int) -> int:
        # dp[i] = number of unique BSTs with i nodes
        dp = [0] * (n + 1)
        dp[0] = 1  # empty tree
        
        for i in range(1, n + 1):
            for j in range(i):
                # j nodes in left subtree, i-j-1 nodes in right subtree
                dp[i] += dp[j] * dp[i - j - 1]
        
        return dp[n]
