// ──────────────────────────────────────────────────────────────────────
// LeetCode #211 · Design Add and Search Words Data Structure
// Difficulty : Medium
// Topics     : String, Depth-First Search, Design, Trie
// URL        : https://leetcode.com/problems/design-add-and-search-words-data-structure/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   We use a Trie (prefix tree) where each node has an array of 26 child
//   pointers (for lowercase letters) and a boolean flag indicating if the
//   node marks the end of a word. For addWord, we traverse the trie
//   creating nodes as needed and mark the last node as end. For search, we
//   use recursion or a stack to handle '.' wildcards: when encountering a
//   '.', we try all 26 children; otherwise, we follow the specific child.
//   The recursion returns true if any path leads to a word end. This
//   approach efficiently supports the operations with O(L) time for add
//   and O(26^d * L) worst-case for search where d is number of dots, but
//   given at most 2 dots, it's acceptable.
// 
// Complexity
//   Time  : addWord: O(L), search: O(26^d * L) where L is word length and d is number of dots (max 2)
//   Space : O(N * L) where N is number of words added
// 
// Runtime  : 3 ms
// Memory   : 42.1 MB
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
        boolean isEnd = false;
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
