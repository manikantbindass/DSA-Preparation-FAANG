"""
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
minStack = MinStack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
minStack.getMin() // return -3
minStack.pop()
minStack.top()    // return 0
minStack.getMin() // return -2

Constraints:
- -2^31 <= val <= 2^31 - 1
- Methods pop, top and getMin will always be called on non-empty stacks.
- At most 3 * 10^4 calls will be made to push, pop, top, and getMin.

Topics: Stack, Design
Time Complexity: O(1) - all operations are constant time
Space Complexity: O(n) - for storing elements
"""

class MinStack:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.stack = []
        self.min_stack = [float('inf')]

    def push(self, val: int) -> None:
        """
        Push element val onto stack.
        """
        self.stack.append(val)
        # Push the minimum between current val and current minimum
        self.min_stack.append(min(val, self.min_stack[-1]))

    def pop(self) -> None:
        """
        Removes the element on top of the stack.
        """
        self.stack.pop()
        self.min_stack.pop()

    def top(self) -> int:
        """
        Get the top element.
        """
        return self.stack[-1]

    def getMin(self) -> int:
        """
        Retrieve the minimum element in the stack.
        """
        return self.min_stack[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
