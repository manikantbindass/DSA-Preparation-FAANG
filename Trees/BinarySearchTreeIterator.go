/*
LeetCode Problem 173: Binary Search Tree Iterator
Problem Number: 173
Difficulty: Medium
Link: https://leetcode.com/problems/binary-search-tree-iterator/

Implement the BSTIterator class that represents an iterator over the in-order traversal
of a binary search tree (BST):
- BSTIterator(TreeNode root) initializes an object.
- int next() returns the next smallest number in the BST.
- boolean hasNext() returns true if there exists a next number, or false otherwise.

Example:
Input: ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
       [[[7,3,15,null,null,9,20]], [], [], [], [], [], [], [], [], []]
Output: [null, 3, 7, true, 9, true, 15, true, 20, false]

Constraints:
- The number of nodes in the tree is in the range [1, 10^5].
- 0 <= Node.val <= 10^6
- At most 10^5 calls will be made to next and hasNext.

Topics: Stack, Tree, Depth-First Search, Binary Search Tree, Binary Tree, Iterator
Time Complexity: O(1) for next() and hasNext() on average
Space Complexity: O(h) - where h is the height of the tree
*/

package trees

// TreeNode defines a node in a binary tree.
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

type BSTIterator struct {
    stack []*TreeNode
}

func Constructor(root *TreeNode) BSTIterator {
    iter := BSTIterator{stack: make([]*TreeNode, 0)}
    iter.pushLeft(root)
    return iter
}

func (this *BSTIterator) Next() int {
    node := this.stack[len(this.stack)-1]
    this.stack = this.stack[:len(this.stack)-1]
    // After popping, push the left nodes of the right child
    this.pushLeft(node.Right)
    return node.Val
}

func (this *BSTIterator) HasNext() bool {
    return len(this.stack) > 0
}

func (this *BSTIterator) pushLeft(node *TreeNode) {
    for node != nil {
        this.stack = append(this.stack, node)
        node = node.Left
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
