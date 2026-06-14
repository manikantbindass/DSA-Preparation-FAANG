/*
LeetCode Problem 146: LRU Cache
Problem Number: 146
Difficulty: Medium
Link: https://leetcode.com/problems/lru-cache/

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
- LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
- int get(int key) Return the value of the key if the key exists, otherwise return -1.
- void put(int key, int value) Update the value of the key if the key exists.
  Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity,
  evict the least recently used key.

Example:
Input: ["LRUCache","put","put","get","put","get","put","get","get","get"]
       [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
Output: [null,null,null,1,null,-1,null,-1,3,4]

Constraints:
- 1 <= capacity <= 3000
- 0 <= key <= 10^4
- 0 <= value <= 10^5
- At most 2 * 10^5 calls will be made to get and put.

Topics: Hash Table, Linked List, Design, Doubly-Linked List
Time Complexity: O(1) - for both get and put operations
Space Complexity: O(capacity) - for storing the cache entries
*/

package linkedlist

type Node struct {
    key  int
    val  int
    prev *Node
    next *Node
}

type LRUCache struct {
    capacity int
    size     int
    cache    map[int]*Node
    head     *Node
    tail     *Node
}

func Constructor(capacity int) LRUCache {
    lru := LRUCache{
        capacity: capacity,
        size:     0,
        cache:    make(map[int]*Node),
        head:     &Node{},
        tail:     &Node{},
    }
    lru.head.next = lru.tail
    lru.tail.prev = lru.head
    return lru
}

func (this *LRUCache) removeNode(node *Node) {
    node.prev.next = node.next
    node.next.prev = node.prev
}

func (this *LRUCache) addToFront(node *Node) {
    node.next = this.head.next
    node.prev = this.head
    this.head.next.prev = node
    this.head.next = node
}

func (this *LRUCache) Get(key int) int {
    if node, exists := this.cache[key]; exists {
        this.removeNode(node)
        this.addToFront(node)
        return node.val
    }
    return -1
}

func (this *LRUCache) Put(key int, value int) {
    if node, exists := this.cache[key]; exists {
        node.val = value
        this.removeNode(node)
        this.addToFront(node)
    } else {
        node := &Node{key: key, val: value}
        this.cache[key] = node
        this.addToFront(node)
        this.size++
        
        if this.size > this.capacity {
            lru := this.tail.prev
            delete(this.cache, lru.key)
            this.removeNode(lru)
            this.size--
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
