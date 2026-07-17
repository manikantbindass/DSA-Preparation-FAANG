/*
 * LeetCode Problem 233: Number of Digit One
 * Problem Number: 233
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/number-of-digit-one/
 *
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 6
 *
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 	0 <= n <= 109
 *
 * Example 1:
 * Input: n = 13
 * Output: 6
 *
 * Example 2:
 * Input: n = 0
 * Output: 0
 *
 * Constraints:
 * - 0 <= n <= 109
 *
 * Topics: Math, Dynamic Programming, Recursion
 * Time Complexity: O(n²) typical DP
 * Space Complexity: O(n) or O(n²)
 * Runtime: 0 ms
 * Memory: 42.2 MB
 */

class MyQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();
    public MyQueue() {
    }
    public void push(int x) {
        stk1.push(x);
    }
    public int pop() {
        move();
        return stk2.pop();
    }
    public int peek() {
        move();
        return stk2.peek();
    }
    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }
    private void move() {
        while (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
    }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
