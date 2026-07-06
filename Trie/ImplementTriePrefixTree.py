# ──────────────────────────────────────────────────────────────────────
# LeetCode #208 · Implement Trie (Prefix Tree)
# Difficulty : Medium
# Topics     : Hash Table, String, Design, Trie
# URL        : https://leetcode.com/problems/implement-trie-prefix-tree/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The Trie (prefix tree) is implemented using an array of 26 child
#   pointers (one for each lowercase letter) and a boolean flag to mark
#   the end of a word. The insert method traverses the tree, creating
#   nodes as needed, and sets the end flag on the final node. The search
#   method uses a helper searchPrefix that traverses the tree according to
#   the given string; if the traversal completes, it returns the node, and
#   search checks if that node is an end node. startsWith simply checks if
#   the traversal completes (node is not null). This approach provides
#   O(L) time for each operation, where L is the length of the word or
#   prefix, and O(N * L) space in the worst case for storing all inserted
#   strings.
# 
# Complexity
#   Time  : O(L) per operation, where L is the length of the word or prefix
#   Space : O(N * L) in the worst case, where N is the number of inserted words and L is the average length
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : ["Trie","insert","search","search","startsWith","insert","search"]
#     Output : [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
# 
# Constraints
#   · 1 <= word.length, prefix.length <= 2000
#   · word and prefix consist only of lowercase English letters.
#   · At most 3 * 104 calls
# ──────────────────────────────────────────────────────────────────────

class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word: str) -> None:
        node = self
        for ch in word:
            idx = ord(ch) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self._search_prefix(word)
        return node is not None and node.is_end

    def startsWith(self, prefix: str) -> bool:
        node = self._search_prefix(prefix)
        return node is not None

    def _search_prefix(self, s: str) -> 'Trie':
        node = self
        for ch in s:
            idx = ord(ch) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node
