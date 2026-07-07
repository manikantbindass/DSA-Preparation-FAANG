// ──────────────────────────────────────────────────────────────────────
// LeetCode #212 · Word Search II
// Difficulty : Hard
// Topics     : Array, String, Backtracking, Trie, Matrix
// URL        : https://leetcode.com/problems/word-search-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a Trie (prefix tree) to store all words. Then we perform
//   DFS/backtracking on the board, checking each cell as a starting point.
//   During DFS, we traverse the Trie simultaneously: if the current
//   character exists in the Trie's children, we continue; if we reach a
//   node that marks the end of a word, we add that word to the result set
//   (and optionally mark it as not end to avoid duplicates). We also mark
//   visited cells to avoid reusing the same cell. After exploring all
//   directions, we backtrack by unmarking the cell. This approach
//   efficiently prunes paths that cannot lead to any word.
// 
// Complexity
//   Time  : O(m * n * 4^L) where L is the maximum word length, but with Trie pruning it's much faster in practice. Building Trie: O(total characters in words). DFS: O(m * n * 4^L) worst-case, but typically less due to early termination.
//   Space : O(total characters in words) for the Trie, plus O(L) recursion stack depth.
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//     Output : ["eat","oath"]
//   Example 2:
//     Input  : board = [["a","b"],["c","d"]], words = ["abcb"]
//     Output : []
// 
// Constraints
//   · m == board.length
//   · n == board[i].length
//   · 1 <= m, n <= 12
//   · board[i][j] is a lowercase English letter.
//   · 1 <= words.length <= 3 * 104
//   · 1 <= words[i].length <= 10
//   · words[i] consists of lowercase English letters.
//   · All the strings of words are unique.
// ──────────────────────────────────────────────────────────────────────

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = w;
        }
        return root;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.children[c - 'a'] == null) return;
        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }
        board[i][j] = '#';
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        board[i][j] = c;
    }
}
