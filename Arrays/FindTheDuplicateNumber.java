/*
 * LeetCode Problem 287: Find the Duplicate Number
 * Problem Number: 287
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Given an array of integers nums containing n + 1 integers where each integer is
 * in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and using only
 * constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 *
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 105
 * 	nums.length == n + 1
 * 	1 <= nums[i] <= n
 * 	All the integers in nums appear only once except for precisely one integer
 * which appears two or more times.
 *
 *
 *
 * Follow up:
 *
 * 	How can we prove that at least one duplicate number must exist in nums?
 * 	Can you solve the problem in linear runtime complexity?
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 * Constraints:
 * - 1 <= n <= 105
 * - nums.length == n + 1
 * - 1 <= nums[i] <= n
 * - All the integers in nums appear only
 *
 * Topics: Array, Two Pointers, Binary Search, Bit Manipulation
 * Time Complexity: O(log n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.5 MB
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private boolean hasPeeked;
    private Integer peekedElement;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator =iterator;
    }
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeeked) {
            peekedElement= iterator.next();
            hasPeeked =true;
        }
        return peekedElement;
    }
    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!hasPeeked) {
            return iterator.next();
        }
        Integer result = peekedElement;
        hasPeeked =false;
        peekedElement= null;
        return result;
    }
    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }
}
