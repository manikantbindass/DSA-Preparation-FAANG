# LeetCode Problem 2911: Minimum Changes to Make K Semi-palindromes
# Difficulty: Hard
# Link: https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
# Approach: Precompute the mismatch cost for each substring over its proper divisors, then partition with dynamic programming.
# Time Complexity: O(n^3 log n + k*n^2)
# Space Complexity: O(n^2)


class Solution:
    def minimumChanges(self, s: str, k: int) -> int:
        n = len(s)
        divisors = [[] for _ in range(n + 1)]
        for step in range(1, n // 2 + 1):
            for length in range(2 * step, n + 1, step):
                divisors[length].append(step)

        cost = [[0] * (n + 1) for _ in range(n)]
        for length in range(2, n + 1):
            for start in range(n - length + 1):
                end = start + length
                best = length
                for step in divisors[length]:
                    changes = 0
                    for offset in range(step):
                        left = start + offset
                        right = end - step + offset
                        while left < right:
                            changes += s[left] != s[right]
                            left += step
                            right -= step
                    best = min(best, changes)
                    if best == 0:
                        break
                cost[start][end] = best

        previous = [0] + [n] * n
        for parts in range(1, k + 1):
            current = [n] * (n + 1)
            first_cut = 2 * (parts - 1)
            for end in range(2 * parts, n + 1):
                current[end] = min(
                    previous[cut] + cost[cut][end]
                    for cut in range(first_cut, end - 1)
                )
            previous = current
        return previous[n]
