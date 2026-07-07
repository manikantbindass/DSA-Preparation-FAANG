# ──────────────────────────────────────────────────────────────────────
# LeetCode #212 · Word Search II
# Difficulty : Hard
# Topics     : Array, String, Backtracking, Trie, Matrix
# URL        : https://leetcode.com/problems/word-search-ii/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   We use a Trie (prefix tree) to store all words. Then we perform
#   DFS/backtracking on the board, traversing the Trie simultaneously.
#   When we reach a node that marks the end of a word, we add it to the
#   result set. To avoid revisiting cells, we temporarily mark them as
#   visited (e.g., by changing the character to a special character like
#   '#'). After exploring all directions, we restore the cell. This
#   approach efficiently prunes paths that cannot lead to any word.
# 
# Complexity
#   Time  : O(m * n * 4^L) where L is the maximum word length, but with Trie pruning it's much faster in practice
#   Space : O(total characters in words) for the Trie
# 
# Runtime  : 0 ms
# Memory   : 42.9 MB
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
        
        rows, cols = len(board), len(board[0])
        result = []
        
        def dfs(r, c, parent):
            if r < 0 or r >= rows or c < 0 or c >= cols or board[r][c] not in parent:
                return
            ch = board[r][c]
            node = parent[ch]
            if '#' in node:
                result.append(node.pop('#'))  # remove to avoid duplicates
            
            # Mark visited
            board[r][c] = '#'
            for dr, dc in [(1,0), (-1,0), (0,1), (0,-1)]:
                dfs(r + dr, c + dc, node)
            board[r][c] = ch
        
        for i in range(rows):
            for j in range(cols):
                dfs(i, j, trie)
        return result
