/*
 * LeetCode Problem 1757: Recyclable and Low Fat Products
 * Problem Number: 1757
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/recyclable-and-low-fat-products/
 * 
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | low_fats    | enum    |
 * | recyclable  | enum    |
 * +-------------+---------+
 * 
 * low_fats is an ENUM of type ('Y', 'N') where 'Y' means low fat and 'N' means not.
 * recyclable is an ENUM of type ('Y', 'N') where 'Y' means recyclable and 'N' means not.
 * 
 * Write a solution to find the ids of products that are both low fat and recyclable.
 * Return the result table in any order.
 * 
 * Example 1:
 * Input: 
 * Products table:
 * +-------------+----------+------------+
 * | product_id  | low_fats | recyclable |
 * +-------------+----------+------------+
 * | 0           | Y        | N          |
 * | 1           | Y        | Y          |
 * | 2           | N        | Y          |
 * | 3           | Y        | Y          |
 * | 4           | N        | N          |
 * +-------------+----------+------------+
 * Output: 
 * +-------------+
 * | product_id  |
 * +-------------+
 * | 1           |
 * | 3           |
 * +-------------+
 * 
 * Topics: Database
 * Time Complexity: O(n) - full table scan
 * Space Complexity: O(1) - excluding output
 */

SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';
