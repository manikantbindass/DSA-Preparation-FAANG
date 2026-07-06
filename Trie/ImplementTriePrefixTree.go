// ──────────────────────────────────────────────────────────────────────
// LeetCode #208 · Implement Trie (Prefix Tree)
// Difficulty : Medium
// Topics     : Hash Table, String, Design, Trie
// URL        : https://leetcode.com/problems/implement-trie-prefix-tree/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The Trie (prefix tree) is implemented using an array of 26 child
//   pointers (one for each lowercase letter) and a boolean flag to mark
//   the end of a word. Insertion traverses the tree, creating nodes as
//   needed, and marks the final node as a word end. Search follows the
//   path character by character; if the path exists and the final node is
//   marked as end, the word is found. startsWith is similar but only
//   checks if the path exists. The searchPrefix helper method encapsulates
//   the traversal logic.
// 
// Complexity
//   Time  : O(L) for each operation, where L is the length of the word or prefix
//   Space : O(N * L) where N is the number of inserted words and L is the average length, due to node creation
// 
// Runtime  : 3 ms
// Memory   : 42.4 MB
// 
// Examples
//   Example 1:
//     Input  : ["Trie","insert","search","search","startsWith","insert","search"]
//     Output : [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
// 
// Constraints
//   · 1 <= word.length, prefix.length <= 2000
//   · word and prefix consist only of lowercase English letters.
//   · At most 3 * 104 calls
// ──────────────────────────────────────────────────────────────────────

type Trie struct {
    children [26]*Trie
    isEnd    bool
}

func Constructor() Trie {
    return Trie{}
}

func (t *Trie) Insert(word string) {
    node := t
    for _, ch := range word {
        idx := ch - 'a'
        if node.children[idx] == nil {
            node.children[idx] = &Trie{}
        }
        node = node.children[idx]
    }
    node.isEnd = true
}

func (t *Trie) Search(word string) bool {
    node := t.searchPrefix(word)
    return node != nil && node.isEnd
}

func (t *Trie) StartsWith(prefix string) bool {
    node := t.searchPrefix(prefix)
    return node != nil
}

func (t *Trie) searchPrefix(s string) *Trie {
    node := t
    for _, ch := range s {
        idx := ch - 'a'
        if node.children[idx] == nil {
            return nil
        }
        node = node.children[idx]
    }
    return node
}
