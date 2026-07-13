"""
LeetCode Problem 225: Implement Stack using Queues
Problem Number: 225
Difficulty: Easy
Link: https://leetcode.com/problems/implement-stack-using-queues/



Topics: Stack, Design, Queue
Time Complexity: See solution
Space Complexity: O(1) to O(n)
Runtime: 1
Memory: 43072000
"""

"""
Original Solution (java) — translate to Python:

class MyStack {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public MyStack() {
    }
    public void push(int x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        Deque<Integer> q = q1;
        q1 = q2;
        q2 = q;
    }
    public int pop() {
        return q1.poll();
    }
    public int top() {
        return q1.peek();
    }
    public boolean empty() {
        return q1.isEmpty();
    }
}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
"""

from typing import List, Optional

class Solution:
    def solve(self) -> None:
        # TODO: Implement Python solution
        pass
