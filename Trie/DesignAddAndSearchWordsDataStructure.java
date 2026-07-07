// ──────────────────────────────────────────────────────────────────────
// LeetCode #211 · Design Add and Search Words Data Structure
// Difficulty : Medium
// Topics     : String, Depth-First Search, Design, Trie
// URL        : https://leetcode.com/problems/design-add-and-search-words-data-structure/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a Trie (prefix tree) where each node has an array of 26 child
//   pointers (for 'a' to 'z') and a boolean flag indicating if the node is
//   the end of a word. For addWord, we traverse the trie creating nodes as
//   needed and mark the last node as end. For search, we use DFS (or
//   recursion) to handle '.' wildcards: at each '.' we try all 26
//   children; otherwise we follow the specific child. The search returns
//   true if any path reaches a node that is end of word and the word
//   length matches.
// 
// Complexity
//   Time  : O(N) for addWord (N = word length), O(26^D) worst-case for search where D is number of dots (but D ≤ 2 per constraints, so effectively O(N) average).
//   Space : O(total characters inserted) for the trie.
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//     Output : [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
// 
// Constraints
//   · 1 <= word.length <= 25
//   · word in addWord consists of lowercase English letters.
//   · word in search consist of '.' or lowercase English letters.
//   · There will be at most 2 dots in word for search queries.
//   · At most 104 calls will be made to addWord and search.
// ──────────────────────────────────────────────────────────────────────

class WordDictionary {
    private TrieNode root;

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int pos, TrieNode node) {
        if (node == null) return false;
        if (pos == word.length()) return node.isEnd;
        char c = word.charAt(pos);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && searchInNode(word, pos + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            int idx = c - 'a';
            return searchInNode(word, pos + 1, node.children[idx]);
        }
    }
}
