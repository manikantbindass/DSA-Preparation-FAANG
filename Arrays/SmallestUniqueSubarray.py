# LeetCode Problem 3934: Smallest Unique Subarray
# Difficulty: Hard
# Link: https://leetcode.com/problems/smallest-unique-subarray/
# Approach: Build a suffix array by rank doubling, then use neighboring common prefix lengths to find unique prefixes.
# Time Complexity: O(n log n + M), where M is the maximum value
# Space Complexity: O(n + M)


class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        n = len(nums)
        suffixes = self._suffix_array(nums)
        positions = [0] * n
        for rank, start in enumerate(suffixes):
            positions[start] = rank

        # lcp[rank] is the common prefix with the preceding suffix.
        lcp = [0] * (n + 1)
        common = 0
        for start, rank in enumerate(positions):
            if rank == 0:
                common = 0
                continue
            previous = suffixes[rank - 1]
            while start + common < n and previous + common < n:
                if nums[start + common] != nums[previous + common]:
                    break
                common += 1
            lcp[rank] = common
            common = max(0, common - 1)

        answer = n
        for rank, start in enumerate(suffixes):
            length = max(lcp[rank], lcp[rank + 1]) + 1
            if length <= n - start:
                answer = min(answer, length)
        return answer

    def _suffix_array(self, nums: list[int]) -> list[int]:
        n = len(nums)
        suffixes = sorted(range(n), key=nums.__getitem__)
        ranks = [0] * n
        classes = 1
        for i in range(1, n):
            if nums[suffixes[i - 1]] != nums[suffixes[i]]:
                classes += 1
            ranks[suffixes[i]] = classes - 1

        width = 1
        shifted = [0] * n
        next_ranks = [0] * n
        while classes < n:
            # The second half is empty for the last `width` suffixes.
            shifted[:width] = range(n - width, n)
            index = width
            for start in suffixes:
                if start >= width:
                    shifted[index] = start - width
                    index += 1

            counts = [0] * classes
            for start in shifted:
                counts[ranks[start]] += 1
            for i in range(1, classes):
                counts[i] += counts[i - 1]
            for start in reversed(shifted):
                rank = ranks[start]
                counts[rank] -= 1
                suffixes[counts[rank]] = start

            classes = 1
            previous = suffixes[0]
            next_ranks[previous] = 0
            for i in range(1, n):
                start = suffixes[i]
                left = ranks[previous + width] if previous + width < n else -1
                right = ranks[start + width] if start + width < n else -1
                if ranks[previous] != ranks[start] or left != right:
                    classes += 1
                next_ranks[start] = classes - 1
                previous = start
            ranks, next_ranks = next_ranks, ranks
            width *= 2
        return suffixes
