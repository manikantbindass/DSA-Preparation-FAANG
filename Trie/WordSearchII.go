// ──────────────────────────────────────────────────────────────────────
// LeetCode #212 · Word Search II
// Difficulty : Hard
// Topics     : Array, String, Backtracking, Trie, Matrix
// URL        : https://leetcode.com/problems/word-search-ii/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a Trie (prefix tree) to store all words. Then we perform
//   DFS/backtracking on the board, traversing the Trie simultaneously.
//   When we reach a node that marks the end of a word, we add it to the
//   result set. To avoid revisiting cells, we temporarily mark them as
//   visited (e.g., by changing the character to a special character like
//   '#'). After exploring all directions, we restore the cell. This
//   approach efficiently prunes paths that cannot lead to any word.
// 
// Complexity
//   Time  : O(m * n * 4^L) where L is the maximum word length, but with Trie pruning it's much faster in practice
//   Space : O(total characters in words) for the Trie
// 
// Runtime  : 0 ms
// Memory   : 42.9 MB
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

type TrieNode struct {
    children [26]*TrieNode
    word     string
}

func findWords(board [][]byte, words []string) []string {
    // Build Trie
    root := &TrieNode{}
    for _, word := range words {
        node := root
        for _, ch := range word {
            idx := ch - 'a'
            if node.children[idx] == nil {
                node.children[idx] = &TrieNode{}
            }
            node = node.children[idx]
        }
        node.word = word
    }
    
    rows, cols := len(board), len(board[0])
    result := []string{}
    
    var dfs func(r, c int, node *TrieNode)
    dfs = func(r, c int, node *TrieNode) {
        if r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] == '#' {
            return
        }
        ch := board[r][c]
        idx := ch - 'a'
        if node.children[idx] == nil {
            return
        }
        next := node.children[idx]
        if next.word != "" {
            result = append(result, next.word)
            next.word = "" // avoid duplicates
        }
        
        // Mark visited
        board[r][c] = '#'
        dfs(r+1, c, next)
        dfs(r-1, c, next)
        dfs(r, c+1, next)
        dfs(r, c-1, next)
        board[r][c] = ch
    }
    
    for i := 0; i < rows; i++ {
        for j := 0; j < cols; j++ {
            dfs(i, j, root)
        }
    }
    return result
}
