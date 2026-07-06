// ──────────────────────────────────────────────────────────────────────
// LeetCode #208 · Implement Trie (Prefix Tree)
// Difficulty : Medium
// Topics     : Hash Table, String, Design, Trie
// URL        : https://leetcode.com/problems/implement-trie-prefix-tree/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The Trie (prefix tree) is implemented using an array of 26 child
//   pointers (one for each lowercase letter) and a boolean flag to mark
//   the end of a word. The insert method traverses the tree, creating
//   nodes as needed, and sets the end flag on the final node. The search
//   method uses a helper searchPrefix that traverses the tree according to
//   the given string; if the traversal completes, it returns the node, and
//   search checks if that node is an end node. startsWith simply checks if
//   the traversal completes (node is not null). This approach provides
//   O(L) time for each operation, where L is the length of the word or
//   prefix, and O(N * L) space in the worst case for storing all inserted
//   strings.
// 
// Complexity
//   Time  : O(L) per operation, where L is the length of the word or prefix
//   Space : O(N * L) in the worst case, where N is the number of inserted words and L is the average length
// 
// Runtime  : 
// Memory   : 
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
