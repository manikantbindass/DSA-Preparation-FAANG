/*
 * LeetCode Problem 146: LRU Cache
 * Problem Number: 146
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/lru-cache/
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists.
 *   Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity,
 *   evict the least recently used key.
 * 
 * Example:
 * Input: ["LRUCache","put","put","get","put","get","put","get","get","get"]
 *        [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 * Output: [null,null,null,1,null,-1,null,-1,3,4]
 * 
 * Constraints:
 * - 1 <= capacity <= 3000
 * - 0 <= key <= 10^4
 * - 0 <= value <= 10^5
 * - At most 2 * 10^5 calls will be made to get and put.
 * 
 * Topics: Hash Table, Linked List, Design, Doubly-Linked List
 * Time Complexity: O(1) - for both get and put operations
 * Space Complexity: O(capacity) - for storing the cache entries
 */

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int val;
    Node prev;
    Node next;
    
    Node() {}
    
    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    private int capacity;
    private int size;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        // Initialize dummy head and tail
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        // Move to front (most recently used)
        removeNode(node);
        addToFront(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            // Move to front (most recently used)
            removeNode(node);
            addToFront(node);
        } else {
            Node node = new Node(key, value);
            cache.put(key, node);
            addToFront(node);
            size++;
            
            if (size > capacity) {
                // Remove least recently used (node before tail)
                Node lru = tail.prev;
                cache.remove(lru.key);
                removeNode(lru);
                size--;
            }
        }
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
