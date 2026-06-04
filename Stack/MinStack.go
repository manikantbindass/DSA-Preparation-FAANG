/*
LeetCode Problem 155: Min Stack
Problem Number: 155
Difficulty: Medium
Link: https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:
- MinStack() initializes the stack object.
- void push(int val) pushes the element val onto the stack.
- void pop() removes the element on the top of the stack.
- int top() gets the top element of the stack.
- int getMin() retrieves the minimum element in the stack.

Example:
Input: ["MinStack","push","push","push","getMin","pop","top","getMin"]
       [[],[-2],[0],[-3],[],[],[],[]]
Output: [null,null,null,null,-3,null,0,-2]

Explanation:
minStack := Constructor()
minStack.Push(-2)
minStack.Push(0)
minStack.Push(-3)
minStack.GetMin() // return -3
minStack.Pop()
minStack.Top()    // return 0
minStack.GetMin() // return -2

Constraints:
- -2^31 <= val <= 2^31 - 1
- Methods Pop, Top and GetMin will always be called on non-empty stacks.
- At most 3 * 10^4 calls will be made to Push, Pop, Top, and GetMin.

Topics: Stack, Design
Time Complexity: O(1) - all operations are constant time
Space Complexity: O(n) - for storing elements
*/

package stack

import "math"

type MinStack struct {
    stack    []int
    minStack []int
}

func Constructor() MinStack {
    return MinStack{
        stack:    make([]int, 0),
        minStack: []int{math.MaxInt32},
    }
}

func (this *MinStack) Push(val int) {
    this.stack = append(this.stack, val)
    // Push the minimum between current val and current minimum
    minVal := val
    if this.minStack[len(this.minStack)-1] < minVal {
        minVal = this.minStack[len(this.minStack)-1]
    }
    this.minStack = append(this.minStack, minVal)
}

func (this *MinStack) Pop() {
    this.stack = this.stack[:len(this.stack)-1]
    this.minStack = this.minStack[:len(this.minStack)-1]
}

func (this *MinStack) Top() int {
    return this.stack[len(this.stack)-1]
}

func (this *MinStack) GetMin() int {
    return this.minStack[len(this.minStack)-1]
}

/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */
