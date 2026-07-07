# ──────────────────────────────────────────────────────────────────────
# LeetCode #212 · Word Search II
# Difficulty : Hard
# Topics     : Array, String, Backtracking, Trie, Matrix
# URL        : https://leetcode.com/problems/word-search-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use a Trie (prefix tree) to store all words. Then we perform
#   DFS/backtracking on the board, checking each cell as a starting point.
#   During DFS, we traverse the Trie simultaneously: if the current
#   character exists in the Trie's children, we continue; if we reach a
#   node that marks the end of a word, we add that word to the result set
#   (and optionally mark it as not end to avoid duplicates). We also mark
#   visited cells to avoid reusing the same cell. After exploring all
#   directions, we backtrack by unmarking the cell. This approach
#   efficiently prunes paths that cannot lead to any word.
# 
# Complexity
#   Time  : O(m * n * 4^L) where L is the maximum word length, but with Trie pruning it's much faster in practice. Building Trie: O(total characters in words). DFS: O(m * n * 4^L) worst-case, but typically less due to early termination.
#   Space : O(total characters in words) for the Trie, plus O(L) recursion stack depth.
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
#     Output : ["eat","oath"]
#   Example 2:
#     Input  : board = [["a","b"],["c","d"]], words = ["abcb"]
#     Output : []
# 
# Constraints
#   · m == board.length
#   · n == board[i].length
#   · 1 <= m, n <= 12
#   · board[i][j] is a lowercase English letter.
#   · 1 <= words.length <= 3 * 104
#   · 1 <= words[i].length <= 10
#   · words[i] consists of lowercase English letters.
#   · All the strings of words are unique.
# ──────────────────────────────────────────────────────────────────────

class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        # Build Trie
        trie = {}
        for word in words:
            node = trie
            for ch in word:
                if ch not in node:
                    node[ch] = {}
                node = node[ch]
            node['#'] = word  # mark end of word
        
        m, n = len(board), len(board[0])
        result = []
        
        def dfs(i, j, parent):
            if i < 0 or i >= m or j < 0 or j >= n:
                return
            ch = board[i][j]
            if ch not in parent:
                return
            node = parent[ch]
            if '#' in node:
                result.append(node['#'])
                del node['#']  # avoid duplicates
            board[i][j] = '#'
            dfs(i + 1, j, node)
            dfs(i - 1, j, node)
            dfs(i, j + 1, node)
            dfs(i, j - 1, node)
            board[i][j] = ch
        
        for i in range(m):
            for j in range(n):
                dfs(i, j, trie)
        return result
