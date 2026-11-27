# LeetCode Problem 3331: Find Subtree Sizes After Changes
# Difficulty: Medium
# Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/
# Approach: Traverse the original tree iteratively and accumulate each subtree at its new parent on exit.
# Time Complexity: O(n)
# Space Complexity: O(n)


class Solution:
    def findSubtreeSizes(self, parent: list[int], s: str) -> list[int]:
        n = len(parent)
        children = [[] for _ in parent]
        for node in range(1, n):
            children[parent[node]].append(node)

        nearest = [-1] * 26
        previous = [-1] * n
        size = [1] * n
        stack = [0]
        while stack:
            event = stack.pop()
            if event >= 0:
                node = event
                letter = ord(s[node]) - ord("a")
                previous[node] = nearest[letter]
                nearest[letter] = node
                # The complemented node marks the exit after all original children.
                stack.append(~node)
                stack.extend(children[node])
            else:
                node = ~event
                letter = ord(s[node]) - ord("a")
                ancestor = previous[node]
                nearest[letter] = ancestor
                new_parent = ancestor if ancestor != -1 else parent[node]
                if new_parent != -1:
                    size[new_parent] += size[node]
        return size
